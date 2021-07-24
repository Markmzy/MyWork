package com.markmzy.mywork.wx.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @title: OAuth2Filter
 * @Author Zhiyue Ma
 * @Date: 7/19/21 10:29
 * @Version 1.0
 */
@Component
@Scope("prototype") //多例对象
public class OAuth2Filter extends AuthenticatingFilter
{
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${mywork.jwt.cache-expire}")
    private int cacheExpire;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest req = (HttpServletRequest) request;

        //获取请求token
        String token = getRequestToken((req));

        if(StringUtils.isBlank(token))
            return null;

        return new OAuth2Token(token);
    }

    public String getRequestToken(HttpServletRequest request)
    {
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token))
        {
            token = request.getParameter("token");
        }

        return token;
    }

    /**
     * 判断是否需要Shiro处理
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getMethod().equals(RequestMethod.OPTIONS.name()); //如果是OPTIONS请求就放行，否组就由Shiro框架处理
    }

    /**
     * 需要Shiro处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //允许跨域
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));

        threadLocalToken.clear();

        String token = getRequestToken(req);
        if(StrUtil.isBlank(token))
        {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }

        try
        {
            jwtUtil.verifierToken(token);
        } catch(TokenExpiredException e)
        {
            if(redisTemplate.hasKey(token)) //如果redis里令牌没过期, 刷新token
            {
                redisTemplate.delete(token);
                int userId = jwtUtil.getUserId(token);
                token = jwtUtil.createToken(userId);
                redisTemplate.opsForValue().set(token, userId + "", cacheExpire, TimeUnit.DAYS);
                threadLocalToken.setToken(token);
            }
            else //如果redis里令牌过期了，重新登陆
            {
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().println("令牌已过期");
                return false;
            }
        } catch(Exception e) //如果解码失败
        {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().println("无效的令牌");
            return false;
        }

        boolean bool = executeLogin(request, response);
        return bool;
    }

    /**
     * 认证失败
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        try
        {
            resp.getWriter().print(e.getMessage());
        } catch(Exception exception)
        {

        }

        return false;
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        super.doFilterInternal(request, response, chain);

    }
}

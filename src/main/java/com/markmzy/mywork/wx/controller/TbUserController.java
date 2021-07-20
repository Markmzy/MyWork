package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.LoginForm;
import com.markmzy.mywork.wx.controller.form.RegisterForm;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"用户表"})
@RestController
@RequestMapping("/user")
public class TbUserController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbUserService tbUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${mywork.jwt.cache-expire}")
    private int cacheExpire;

    @ApiOperation("用户注册")
    @RequestMapping("/register")
    public R register(@Valid @RequestBody RegisterForm form)
    {
        int id = tbUserService.register(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        String token = jwtUtil.createToken(id);
        Set<String> permissions = tbUserService.getPermissions(id);
        saveCacheToken(token, id);
        return Objects.requireNonNull(R.ok("用户注册成功").put("token", token)).put("permissions", permissions);
    }

    @ApiOperation("用户登陆")
    @RequestMapping("/login")
    public R login(@Valid @RequestBody LoginForm form)
    {
        int id = tbUserService.login(form.getCode());
        String token = jwtUtil.createToken(id);
        Set<String> permissions = tbUserService.getPermissions(id);
        saveCacheToken(token, id);
        return Objects.requireNonNull(R.ok("用户登陆成功").put("token", token)).put("permissions", permissions);
    }

    private void saveCacheToken(String token, int id)
    {
        redisTemplate.opsForValue().set(token, id + "", cacheExpire, TimeUnit.DAYS);
    }


}

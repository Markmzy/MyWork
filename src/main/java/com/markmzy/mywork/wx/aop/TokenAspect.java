package com.markmzy.mywork.wx.aop;

import com.markmzy.mywork.wx.config.shiro.ThreadLocalToken;
import com.markmzy.mywork.wx.util.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title: TokenAspect
 * @Author Zhiyue Ma
 * @Date: 7/19/21 12:53
 * @Version 1.0
 */

@Aspect
@Component
public class TokenAspect
{
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * com.markmzy.mywork.wx.controller.*.*(..)))")
    public void aspect()
    {

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        R r = (R) point.proceed();
        String token = threadLocalToken.getToken();
        if(token != null)
        {
            r.put("token", token); //把获得的新token加进R里
            threadLocalToken.clear();
        }
        return r;
    }
}

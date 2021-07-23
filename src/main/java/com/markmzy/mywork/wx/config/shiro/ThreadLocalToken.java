package com.markmzy.mywork.wx.config.shiro;

import org.springframework.stereotype.Component;

/**
 * @title: ThreadLocalToken
 * @Author Zhiyue Ma
 * @Date: 7/19/21 10:52
 * @Version 1.0
 */
@Component
public class ThreadLocalToken
{
    private ThreadLocal local = new ThreadLocal();

    public String getToken()
    {
        return (String) local.get();
    }

    public void setToken(String token)
    {
        local.set(token);
    }

    public void clear()
    {
        local.remove();
    }

}

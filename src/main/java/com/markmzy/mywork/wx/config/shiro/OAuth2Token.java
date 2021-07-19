package com.markmzy.mywork.wx.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @title: OAuth2Token
 * @Author Zhiyue Ma
 * @Date: 7/19/21 09:43
 * @Version 1.0
 */
public class OAuth2Token implements AuthenticationToken
{
    private String token;

    public OAuth2Token(String token)
    {
        this.token = token;
    }

    @Override
    public Object getPrincipal()
    {
        return token;
    }

    @Override
    public Object getCredentials()
    {
        return token;
    }
}

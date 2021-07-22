package com.markmzy.mywork.wx.config.shiro;

import com.markmzy.mywork.wx.model.TbUser;
import com.markmzy.mywork.wx.service.ITbUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @title: OAuth2Realm
 * @Author Zhiyue Ma
 * @Date: 7/19/21 09:45
 * @Version 1.0
 */

@Component
public class OAuth2Realm extends AuthorizingRealm
{
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ITbUserService tbUserService;

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权 (验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        TbUser user = (TbUser) principalCollection.getPrimaryPrincipal();
        int userId = user.getId();
        //用户权限列表
        Set<String> permissions = tbUserService.searchPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证 (验证登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        String token = (String) authenticationToken.getPrincipal();
        int userId = jwtUtil.getUserId(token);
        TbUser user = tbUserService.searchUserById(userId);
        if(user == null)
            throw new LockedAccountException("账号已被锁定，请联系管理员");

        //需要三个参数 用户信息, token, realmName
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, getName());
        return info;
    }
}
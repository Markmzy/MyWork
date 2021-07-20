package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbUser;

import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbUserService extends IService<TbUser>
{
    /**
     * 获取用户ID
     */
    String getOpenId(String code);

    /**
     * 注册
     */
    Integer register(String registerCode, String code, String nickname, String photo);

    /**
     * 获取用户权限
     */
    Set<String> getPermissions(int id);

    /**
     * 登陆
     */
    Integer login(String code);

    /**
     * 查询用户信息
     */
    TbUser getUserById(int userId);

}

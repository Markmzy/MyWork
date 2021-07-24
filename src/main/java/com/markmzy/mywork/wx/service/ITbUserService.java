package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    String searchOpenId(String code);

    /**
     * 注册
     */
    Integer register(String registerCode, String code, String nickname, String photo);

    /**
     * 获取用户权限
     */
    Set<String> searchPermissions(int id);

    /**
     * 登陆
     */
    Integer login(String code);

    /**
     * 查询用户信息
     */
    TbUser searchUserById(int userId);

    /**
     * 查询用户入职时间
     */
    String searchHireDate(int userId);

    /**
     * 查询用户概要信息
     */
    HashMap searchUserSummary(int userId);

    /**
     * 查询员工列表，按照部门分组
     */
    ArrayList<HashMap> searchUserGroupByDept(String keyword);

    /**
     * 查询会议成员
     */
    ArrayList<HashMap> searchMembers(List param);

}

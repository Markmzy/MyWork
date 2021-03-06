package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbUserMapper extends BaseMapper<TbUser>
{
    boolean haveRootUser();

    void insertUser(HashMap map);

    Integer searchIdByOpenId(String openId);

    Set<String> searchPermissionsById(int id);

    TbUser searchUserById(int userId);

    HashMap<String, String> searchNameAndDept(int userId);

    String searchHireDate(int userId);

    HashMap searchUserSummary(int userId);

    ArrayList<HashMap> searchUserGroupByDept(String keyword);

    ArrayList<HashMap> searchMembers(List param);

    String searchMemberEmail(int id);

}

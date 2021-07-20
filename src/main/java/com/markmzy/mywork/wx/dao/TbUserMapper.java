package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbUser;

import java.util.HashMap;
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
    public boolean haveRootUser();

    public void insertUser(HashMap map);

    public Integer searchIdByOpenId(String openId);

    public Set<String> searchPermissionsById(int id);
}

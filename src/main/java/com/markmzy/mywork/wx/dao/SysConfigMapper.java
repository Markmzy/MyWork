package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.SysConfig;

import java.util.List;

/**
 * <p>
 * 系统设置表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface SysConfigMapper extends BaseMapper<SysConfig>
{
    public List<SysConfig> selectAllParam();
}

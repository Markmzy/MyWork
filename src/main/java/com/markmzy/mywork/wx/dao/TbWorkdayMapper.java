package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbWorkday;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 工作日表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbWorkdayMapper extends BaseMapper<TbWorkday>
{
    Integer searchTodayIsWorkDay();

    ArrayList<String> searchWorkdayInRange(HashMap param);
}

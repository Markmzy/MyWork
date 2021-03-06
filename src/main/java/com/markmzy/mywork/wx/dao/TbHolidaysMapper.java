package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbHolidays;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 节假日表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbHolidaysMapper extends BaseMapper<TbHolidays>
{
    Integer searchTodayIsHoliday();

    ArrayList<String> searchHolidaysInRange(HashMap param);
}

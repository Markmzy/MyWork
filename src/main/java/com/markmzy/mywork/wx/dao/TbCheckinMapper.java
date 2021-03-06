package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbCheckin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 签到表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbCheckinMapper extends BaseMapper<TbCheckin>
{
    Integer haveCheckin(HashMap param);

    void insertCheckin(TbCheckin checkin);

    HashMap searchTodayCheckin(int userId);

    public long searchCheckinDays(int userId);

    public ArrayList<HashMap> searchWeekCheckin(HashMap param);
}

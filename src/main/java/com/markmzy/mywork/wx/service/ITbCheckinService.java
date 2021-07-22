package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbCheckin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbCheckinService extends IService<TbCheckin>
{
    /**
     * 是否可以签到
     */
    String validCanCheckIn(int userId, String date);

    /**
     * 签到
     */
    void checkin(HashMap param);

    /**
     * 生成人脸模型
     */
    void createFaceModel(int userId, String path);

    /**
     * 今天签到情况
     */
    HashMap searchTodayCheckIn(int userId);

    /**
     * 总共签到情况
     */
    Long searchCheckInDays(int userId);

    /**
     * 一周签到情况
     */
    ArrayList<HashMap> searchWeekCheckIn(HashMap param);

    /**
     * 一月签到情况
     */
    ArrayList<HashMap> searchMonthCheckIn(HashMap param);


}

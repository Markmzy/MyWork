package com.markmzy.mywork.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markmzy.mywork.wx.model.TbMeeting;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 服务类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface ITbMeetingService extends IService<TbMeeting>
{
    /**
     * 添加会议
     */
    void insertMeeting(TbMeeting meeting);

    /**
     * 分页查询会议列表
     */
    ArrayList<HashMap> searchMeetingListByPage(HashMap param);
}

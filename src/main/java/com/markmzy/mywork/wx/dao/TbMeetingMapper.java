package com.markmzy.mywork.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markmzy.mywork.wx.model.TbMeeting;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 Mapper 接口
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
public interface TbMeetingMapper extends BaseMapper<TbMeeting>
{
    int insertMeeting(TbMeeting meeting);

    ArrayList<HashMap> searchMeetingListByPage(HashMap param);

    HashMap searchMeetingById(int id);

    ArrayList<HashMap> searchMeetingMembers(int id);

    int updateMeeting(HashMap param);

    int deleteMeetingById(int id);




}

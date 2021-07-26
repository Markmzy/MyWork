package com.markmzy.mywork.wx.service.impl;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.dao.TbMeetingMapper;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.TbMeeting;
import com.markmzy.mywork.wx.service.ITbMeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Service
@Slf4j
public class TbMeetingServiceImpl extends ServiceImpl<TbMeetingMapper, TbMeeting> implements ITbMeetingService
{
    @Autowired
    private TbMeetingMapper tbMeetingMapper;

    @Override
    public void insertMeeting(TbMeeting meeting)
    {
        int row = tbMeetingMapper.insertMeeting(meeting);
        if(row != 1)
            throw new MyException("会议添加失败");

        //工作流

    }

    @Override
    public ArrayList<HashMap> searchMeetingListByPage(HashMap param)
    {
        ArrayList<HashMap> list = tbMeetingMapper.searchMeetingListByPage(param);
        String date = null;
        ArrayList resultList = new ArrayList();
        HashMap resultMap = null;
        JSONArray array = null;
        for(HashMap map : list)
        {
            String temp = map.get("date").toString();
            if(!temp.equals(date))
            {
                date = temp;
                resultMap = new HashMap();
                resultMap.put("date", date);
                array = new JSONArray();
                resultMap.put("list", array);
                resultList.add(resultMap);
            }
            array.put(map);
        }
        return resultList;
    }

    @Override
    public HashMap searchMeetingById(int id)
    {
        HashMap map = tbMeetingMapper.searchMeetingById(id);
        ArrayList<HashMap> list = tbMeetingMapper.searchMeetingMembers(id);
        map.put("members", list);
        return map;
    }

    @Override
    public void updateMeeting(HashMap param)
    {
        int row = tbMeetingMapper.updateMeeting(param);
        if(row != 1)
            throw new MyException("会议更新失败");
    }

    @Override
    public void deleteMeetingById(int id)
    {
        int row = tbMeetingMapper.deleteMeetingById(id);
        if(row != 1)
            throw new MyException("会议删除失败");
    }
}

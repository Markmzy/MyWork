package com.markmzy.mywork.wx.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markmzy.mywork.wx.config.SysConstants;
import com.markmzy.mywork.wx.dao.TbCheckinMapper;
import com.markmzy.mywork.wx.dao.TbHolidaysMapper;
import com.markmzy.mywork.wx.dao.TbWorkdayMapper;
import com.markmzy.mywork.wx.model.TbCheckin;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Scope("prototype")
@Service
@Slf4j
public class TbCheckinServiceImpl extends ServiceImpl<TbCheckinMapper, TbCheckin> implements ITbCheckinService
{
    @Autowired
    private SysConstants sysConstants;

    @Autowired
    private TbHolidaysMapper tbHolidaysMapper;

    @Autowired
    private TbWorkdayMapper tbWorkdayMapper;

    @Autowired
    private TbCheckinMapper tbCheckinMapper;

    @Override
    public String validCanCheckIn(int userId, String date)
    {
        boolean isHoliday = tbHolidaysMapper.searchtodayIsHoliday() != null ? true : false;
        boolean isWorkday = tbWorkdayMapper.searchTodayIsWorkDay() != null ? true : false;

        String type = "工作日";
        if(DateUtil.date().isWeekend())
            type = "节假日";

        if(isHoliday)
            type = "节假日";
        else if(isWorkday)
            type = "工作日";

        if(type.equals("节假日"))
            return "节假日不需要考勤";
        else
        {
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + sysConstants.attendanceStartTime;
            String end = DateUtil.today() + " " + sysConstants.attendanceEndTime;
            DateTime attendanceStart = DateUtil.parse(start);
            DateTime attendanceEnd = DateUtil.parse(end);
            if(now.isBefore(attendanceStart))
                return "没到上班考勤时间";
            else if(now.isAfter(attendanceEnd))
                return "超过了上班考勤结束时间";
            else
            {
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", start);
                map.put("end", end);
                boolean bool = tbCheckinMapper.haveCheckin(map) != null;
                return bool ? "今日已经有考勤记录，无需重复考勤" : "可以考勤";
            }

        }
    }
}

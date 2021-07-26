package com.markmzy.mywork.wx.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.*;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.model.TbMeeting;
import com.markmzy.mywork.wx.service.ITbMeetingService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 会议表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"会议表"})
@RestController
@RequestMapping("/meeting")
public class TbMeetingController
{
    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    private ITbMeetingService tbMeetingService;

    @PostMapping("/searchMeetingListByPage")
    @ApiOperation("查询会议列表分页数据")
    public R searchMeetingListByPage(@Valid @RequestBody SearchMeetingListByPageForm form, @RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        int pageNum = form.getPageNum();
        int pageSize = form.getPageSize();
        long start = (pageNum - 1) * pageSize;
        HashMap map = new HashMap();
        map.put("userId", userId);
        map.put("start", start);
        map.put("pageSize", pageSize);
        ArrayList list = tbMeetingService.searchMeetingListByPage(map);
        return R.ok().put("result", list);
    }

    @PostMapping("/insertMeeting")
    @ApiOperation("创建会议")
    @RequiresPermissions(value = {"ROOT", "MEETING:INSERT"}, logical = Logical.OR)
    public R insertMeeting(@Valid @RequestBody InsertMeetingForm form, @RequestHeader("token") String token)
    {
        if(form.getType() == 2 && (form.getPlace() == null || form.getPlace().length() == 0))
        {
            throw new MyException("线下会议地点不能为空");
        }
        DateTime d1 = DateUtil.parse(form.getDate() + " " + form.getStart() + ":00");
        DateTime d2 = DateUtil.parse(form.getDate() + " " + form.getEnd() + ":00");
        if(d2.isBeforeOrEquals(d1))
        {
            throw new MyException("结束时间必须大于开始时间");
        }
        if(!JSONUtil.isJsonArray(form.getMembers()))
        {
            throw new MyException("members不是JSON数组");
        }

        TbMeeting meeting = new TbMeeting();
        meeting.setUuid(UUID.randomUUID().toString(true));
        meeting.setTitle(form.getTitle());
        meeting.setCreatorId(jwtUtil.getUserId(token));
        meeting.setDate(form.getDate());
        meeting.setPlace(form.getPlace());
        meeting.setStart(form.getStart() + ":00");
        meeting.setEnd(form.getEnd() + ":00");
        meeting.setType(form.getType());
        meeting.setMembers(form.getMembers());
        meeting.setDesc(form.getDesc());
        meeting.setStatus(1);
        tbMeetingService.insertMeeting(meeting);
        return R.ok().put("result", "添加会议成功");
    }

    @PostMapping("/searchMeetingById")
    @ApiOperation("根据会议id查询会议")
    @RequiresPermissions(value = {"ROOT", "MEETING:SELECT"}, logical = Logical.OR)
    public R searchMeetingById(@Valid @RequestBody SearchMeetingByIdForm form, @RequestHeader("token") String token)
    {
        HashMap map = tbMeetingService.searchMeetingById(form.getId());
        return R.ok().put("result", map);
    }

    @PostMapping("/updateMeeting")
    @ApiOperation("修改会议")
    @RequiresPermissions(value = {"ROOT", "MEETING:UPDATE"}, logical = Logical.OR)
    public R updateMeeting(@Valid @RequestBody UpdateMeetingForm form, @RequestHeader("token") String token)
    {
        if(form.getType() == 2 && (form.getPlace() == null || form.getPlace().length() == 0))
        {
            throw new MyException("线下会议地点不能为空");
        }
        DateTime d1 = DateUtil.parse(form.getDate() + " " + form.getStart() + ":00");
        DateTime d2 = DateUtil.parse(form.getDate() + " " + form.getEnd() + ":00");
        if(d2.isBeforeOrEquals(d1))
        {
            throw new MyException("结束时间必须大于开始时间");
        }
        if(!JSONUtil.isJsonArray(form.getMembers()))
        {
            throw new MyException("members不是JSON数组");
        }

        HashMap map = new HashMap();
        map.put("id", form.getId());
        map.put("title", form.getTitle());
        map.put("date", form.getDate());
        map.put("place", form.getPlace());
        map.put("start", form.getStart() + ":00");
        map.put("end", form.getEnd() + ":00");
        map.put("type", form.getType());
        map.put("members", form.getMembers());
        map.put("desc", form.getDesc());
        tbMeetingService.updateMeeting(map);
        return R.ok().put("result", "修改会议成功");
    }

    @PostMapping("/deleteMeetingById")
    @ApiOperation("根据ID删除会议")
    @RequiresPermissions(value = {"ROOT", "MEETING:DELETE"}, logical = Logical.OR)
    public R deleteMeetingById(@Valid @RequestBody DeleteMeetingByIdForm form){
        tbMeetingService.deleteMeetingById(form.getId());
        return R.ok().put("result","删除会议成功");
    }
}

package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.SearchMeetingListByPageForm;
import com.markmzy.mywork.wx.service.ITbMeetingService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}

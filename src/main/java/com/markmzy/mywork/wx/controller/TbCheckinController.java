package com.markmzy.mywork.wx.controller;

import cn.hutool.core.date.DateUtil;
import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"签到表"})
@RestController
@RequestMapping("/checkin")
public class TbCheckinController
{
    @Resource
    private ITbCheckinService tbCheckinService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/validCanCheckIn")
    @ApiOperation("查看用户今天是否可以签到")
    public R validCanCheckIn(@RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        String result = tbCheckinService.validCanCheckIn(userId, DateUtil.today());
        return R.ok(result);
    }


}

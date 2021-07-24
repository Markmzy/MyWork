package com.markmzy.mywork.wx.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.LoginForm;
import com.markmzy.mywork.wx.controller.form.RegisterForm;
import com.markmzy.mywork.wx.controller.form.SearchMembersForm;
import com.markmzy.mywork.wx.controller.form.SearchUserGroupByDeptForm;
import com.markmzy.mywork.wx.exception.MyException;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"用户表"})
@RestController
@RequestMapping("/user")
public class TbUserController
{
    @Resource
    private ITbUserService tbUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${mywork.jwt.cache-expire}")
    private int cacheExpire;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public R register(@Valid @RequestBody RegisterForm form)
    {
        int id = tbUserService.register(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        String token = jwtUtil.createToken(id);
        Set<String> permissions = tbUserService.searchPermissions(id);
        saveCacheToken(token, id);
        return Objects.requireNonNull(R.ok("用户注册成功").put("token", token)).put("permissions", permissions);
    }

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    public R login(@Valid @RequestBody LoginForm form, @RequestHeader("token") String token)
    {
        Integer id;
        if(StrUtil.isNotEmpty(token))
        {
            jwtUtil.verifierToken(token);   //验证令牌的有效性
            id = jwtUtil.getUserId(token);
        }
        else
        {
            id = tbUserService.login(form.getCode());
            token = jwtUtil.createToken(id);
            saveCacheToken(token, id);
        }

        Set<String> permsSet = tbUserService.searchPermissions(id);
        return Objects.requireNonNull(R.ok("登陆成功").put("token", token)).put("permissions", permsSet);
    }

    private void saveCacheToken(String token, int id)
    {
        redisTemplate.opsForValue().set(token, id + "", cacheExpire, TimeUnit.DAYS);
    }

    @GetMapping("/searchUserSummary")
    @ApiOperation("查询用户概要信息")
    public R searchUserSummary(@RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        HashMap map = tbUserService.searchUserSummary(userId);
        return R.ok().put("result", map);
    }

    @PostMapping("/searchUserGroupByDept")
    @ApiOperation("查询员工列表，按照部门分组")
    @RequiresPermissions(value = {"ROOT", "EMPLOYEE:SELECT"}, logical = Logical.OR)
    public R searchUserGroupByDept(@Valid @RequestBody SearchUserGroupByDeptForm form)
    {
        ArrayList<HashMap> list = tbUserService.searchUserGroupByDept(form.getKeyword());
        return R.ok().put("result", list);
    }

    @PostMapping("/searchMembers")
    @ApiOperation("查询会议成员")
    @RequiresPermissions(value = {"ROOT", "MEETING:INSERT", "MEETING:UPDATE"}, logical = Logical.OR)
    public R searchMembers(@Valid @RequestBody SearchMembersForm form)
    {
        if(!JSONUtil.isJsonArray(form.getMembers()))
        {
            throw new MyException("members不是JSON数组");
        }
        List param = JSONUtil.parseArray(form.getMembers()).toList(Integer.class);
        ArrayList list = tbUserService.searchMembers(param);
        return R.ok().put("result", list);
    }

}

package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.config.shiro.JwtUtil;
import com.markmzy.mywork.wx.controller.form.DeleteMessageRefByIdForm;
import com.markmzy.mywork.wx.controller.form.SearchMessageByIdForm;
import com.markmzy.mywork.wx.controller.form.SearchMessageByPageForm;
import com.markmzy.mywork.wx.controller.form.UpdateUnreadMessageForm;
import com.markmzy.mywork.wx.service.MessageService;
import com.markmzy.mywork.wx.task.MessageTask;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @title: MessageController
 * @Author Zhiyue Ma
 * @Date: 7/22/21 19:47
 * @Version 1.0
 */
@Api(tags = {"消息表"})
@RestController
@RequestMapping("/message")
public class MessageController
{
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageTask messageTask;

    @PostMapping("/searchMessageByPage")
    @ApiOperation("获取分页消息列表")
    public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageForm form, @RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        long pageNum = form.getPageNum();
        int pageSize = form.getPageSize();
        long start = (pageNum - 1) * pageSize; //做一下偏移

        List<HashMap> list = messageService.searchMessageByPage(userId, start, pageSize);
        return R.ok().put("result", list);
    }

    @PostMapping("/searchMessageById")
    @ApiOperation("根据ID查询消息")
    public R searchMessageById(@Valid @RequestBody SearchMessageByIdForm form)
    {
        HashMap map = messageService.searchMessageById(form.getId());
        return R.ok().put("result", map);
    }

    @PostMapping("/updateUnreadMessage")
    @ApiOperation("未读消息更新成已读消息")
    public R updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageForm form)
    {
        long rows = messageService.updateUnreadMessage(form.getId());
        return R.ok().put("result", rows > 0); //成功更新与否
    }

    @PostMapping("/deleteMessageRefById")
    @ApiOperation("删除消息关联")
    public R deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdForm form)
    {
        long rows = messageService.deleteMessageRefById(form.getId());
        return R.ok().put("result", rows > 0); //成功删除与否
    }

    @GetMapping("/refreshMessage")
    @ApiOperation("刷新用户消息")
    public R refreshMessage(@RequestHeader("token") String token)
    {
        int userId = jwtUtil.getUserId(token);
        messageTask.receiveAsync(userId + "");
        long lastRows = messageService.searchLastCount(userId);
        long unreadRows = messageService.searchUnreadCount(userId);
        return Objects.requireNonNull(R.ok().put("lastRows", lastRows)).put("unreadRows", unreadRows);
    }


}

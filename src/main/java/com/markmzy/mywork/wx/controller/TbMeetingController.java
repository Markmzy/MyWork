package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ITbMeetingService;
import com.markmzy.mywork.wx.model.TbMeeting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/tb-meeting")
public class TbMeetingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbMeetingService tbMeetingService;


    @ApiOperation(value = "新增会议表")
    @PostMapping()
    public int add(@RequestBody TbMeeting tbMeeting){
        return tbMeetingService.add(tbMeeting);
    }

    @ApiOperation(value = "删除会议表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbMeetingService.delete(id);
    }

    @ApiOperation(value = "更新会议表")
    @PutMapping()
    public int update(@RequestBody TbMeeting tbMeeting){
        return tbMeetingService.updateData(tbMeeting);
    }

    @ApiOperation(value = "查询会议表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbMeeting> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbMeetingService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询会议表")
    @GetMapping("{id}")
    public TbMeeting findById(@PathVariable Long id){
        return tbMeetingService.findById(id);
    }

}

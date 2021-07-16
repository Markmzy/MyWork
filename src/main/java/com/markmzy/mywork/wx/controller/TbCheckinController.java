package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbCheckin;
import com.markmzy.mywork.wx.service.ITbCheckinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"签到表"})
@RestController
@RequestMapping("/tb-checkin")
public class TbCheckinController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbCheckinService tbCheckinService;


    @ApiOperation(value = "新增签到表")
    @PostMapping()
    public int add(@RequestBody TbCheckin tbCheckin)
    {
        return tbCheckinService.add(tbCheckin);
    }

    @ApiOperation(value = "删除签到表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbCheckinService.delete(id);
    }

    @ApiOperation(value = "更新签到表")
    @PutMapping()
    public int update(@RequestBody TbCheckin tbCheckin)
    {
        return tbCheckinService.updateData(tbCheckin);
    }

    @ApiOperation(value = "查询签到表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbCheckin> findListByPage(@RequestParam Integer page,
                                           @RequestParam Integer pageCount)
    {
        return tbCheckinService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询签到表")
    @GetMapping("{id}")
    public TbCheckin findById(@PathVariable Long id)
    {
        return tbCheckinService.findById(id);
    }

}

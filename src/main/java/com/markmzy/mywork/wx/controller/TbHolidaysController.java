package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ITbHolidaysService;
import com.markmzy.mywork.wx.model.TbHolidays;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 节假日表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"节假日表"})
@RestController
@RequestMapping("/tb-holidays")
public class TbHolidaysController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbHolidaysService tbHolidaysService;


    @ApiOperation(value = "新增节假日表")
    @PostMapping()
    public int add(@RequestBody TbHolidays tbHolidays){
        return tbHolidaysService.add(tbHolidays);
    }

    @ApiOperation(value = "删除节假日表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbHolidaysService.delete(id);
    }

    @ApiOperation(value = "更新节假日表")
    @PutMapping()
    public int update(@RequestBody TbHolidays tbHolidays){
        return tbHolidaysService.updateData(tbHolidays);
    }

    @ApiOperation(value = "查询节假日表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbHolidays> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbHolidaysService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询节假日表")
    @GetMapping("{id}")
    public TbHolidays findById(@PathVariable Long id){
        return tbHolidaysService.findById(id);
    }

}

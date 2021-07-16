package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ISysConfigService;
import com.markmzy.mywork.wx.model.SysConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统设置表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"系统设置表"})
@RestController
@RequestMapping("/sys-config")
public class SysConfigController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysConfigService sysConfigService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody SysConfig sysConfig){
        return sysConfigService.add(sysConfig);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return sysConfigService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody SysConfig sysConfig){
        return sysConfigService.updateData(sysConfig);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<SysConfig> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return sysConfigService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public SysConfig findById(@PathVariable Long id){
        return sysConfigService.findById(id);
    }

}

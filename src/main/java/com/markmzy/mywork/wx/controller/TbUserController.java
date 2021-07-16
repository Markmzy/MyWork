package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ITbUserService;
import com.markmzy.mywork.wx.model.TbUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/tb-user")
public class TbUserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbUserService tbUserService;


    @ApiOperation(value = "新增用户表")
    @PostMapping()
    public int add(@RequestBody TbUser tbUser){
        return tbUserService.add(tbUser);
    }

    @ApiOperation(value = "删除用户表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbUserService.delete(id);
    }

    @ApiOperation(value = "更新用户表")
    @PutMapping()
    public int update(@RequestBody TbUser tbUser){
        return tbUserService.updateData(tbUser);
    }

    @ApiOperation(value = "查询用户表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbUser> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbUserService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询用户表")
    @GetMapping("{id}")
    public TbUser findById(@PathVariable Long id){
        return tbUserService.findById(id);
    }

}

package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ITbPermissionService;
import com.markmzy.mywork.wx.model.TbPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {""})
@RestController
@RequestMapping("/tb-permission")
public class TbPermissionController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbPermissionService tbPermissionService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody TbPermission tbPermission){
        return tbPermissionService.add(tbPermission);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbPermissionService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody TbPermission tbPermission){
        return tbPermissionService.updateData(tbPermission);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbPermission> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbPermissionService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public TbPermission findById(@PathVariable Long id){
        return tbPermissionService.findById(id);
    }

}

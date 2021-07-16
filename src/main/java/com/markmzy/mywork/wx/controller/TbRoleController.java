package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbRole;
import com.markmzy.mywork.wx.service.ITbRoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"角色表"})
@RestController
@RequestMapping("/tb-role")
public class TbRoleController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbRoleService tbRoleService;


    @ApiOperation(value = "新增角色表")
    @PostMapping()
    public int add(@RequestBody TbRole tbRole)
    {
        return tbRoleService.add(tbRole);
    }

    @ApiOperation(value = "删除角色表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbRoleService.delete(id);
    }

    @ApiOperation(value = "更新角色表")
    @PutMapping()
    public int update(@RequestBody TbRole tbRole)
    {
        return tbRoleService.updateData(tbRole);
    }

    @ApiOperation(value = "查询角色表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbRole> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount)
    {
        return tbRoleService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询角色表")
    @GetMapping("{id}")
    public TbRole findById(@PathVariable Long id)
    {
        return tbRoleService.findById(id);
    }

}

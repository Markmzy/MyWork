package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbDept;
import com.markmzy.mywork.wx.service.ITbDeptService;
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
 * 部门表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"部门表"})
@RestController
@RequestMapping("/tb-dept")
public class TbDeptController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbDeptService tbDeptService;


    @ApiOperation(value = "新增部门表")
    @PostMapping()
    public int add(@RequestBody TbDept tbDept)
    {
        return tbDeptService.add(tbDept);
    }

    @ApiOperation(value = "删除部门表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbDeptService.delete(id);
    }

    @ApiOperation(value = "更新部门表")
    @PutMapping()
    public int update(@RequestBody TbDept tbDept)
    {
        return tbDeptService.updateData(tbDept);
    }

    @ApiOperation(value = "查询部门表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbDept> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount)
    {
        return tbDeptService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询部门表")
    @GetMapping("{id}")
    public TbDept findById(@PathVariable Long id)
    {
        return tbDeptService.findById(id);
    }

}

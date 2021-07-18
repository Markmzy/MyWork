package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbModule;
import com.markmzy.mywork.wx.service.ITbModuleService;
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
 * 模块资源表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"模块资源表"})
@RestController
@RequestMapping("/tb-module")
public class TbModuleController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbModuleService tbModuleService;


    @ApiOperation(value = "新增模块资源表")
    @PostMapping()
    public int add(@RequestBody TbModule tbModule)
    {
        return tbModuleService.add(tbModule);
    }

    @ApiOperation(value = "删除模块资源表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbModuleService.delete(id);
    }

    @ApiOperation(value = "更新模块资源表")
    @PutMapping()
    public int update(@RequestBody TbModule tbModule)
    {
        return tbModuleService.updateData(tbModule);
    }

    @ApiOperation(value = "查询模块资源表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbModule> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount)
    {
        return tbModuleService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询模块资源表")
    @GetMapping("{id}")
    public TbModule findById(@PathVariable Long id)
    {
        return tbModuleService.findById(id);
    }

}

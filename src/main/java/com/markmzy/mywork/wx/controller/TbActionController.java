package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbAction;
import com.markmzy.mywork.wx.service.ITbActionService;
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
 * 行为表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"行为表"})
@RestController
@RequestMapping("/tb-action")
public class TbActionController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbActionService tbActionService;


    @ApiOperation(value = "新增行为表")
    @PostMapping()
    public int add(@RequestBody TbAction tbAction)
    {
        return tbActionService.add(tbAction);
    }

    @ApiOperation(value = "删除行为表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbActionService.delete(id);
    }

    @ApiOperation(value = "更新行为表")
    @PutMapping()
    public int update(@RequestBody TbAction tbAction)
    {
        return tbActionService.updateData(tbAction);
    }

    @ApiOperation(value = "查询行为表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbAction> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount)
    {
        return tbActionService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询行为表")
    @GetMapping("{id}")
    public TbAction findById(@PathVariable Long id)
    {
        return tbActionService.findById(id);
    }

}

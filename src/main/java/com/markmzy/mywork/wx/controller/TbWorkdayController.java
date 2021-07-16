package com.markmzy.mywork.wx.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.markmzy.mywork.wx.service.ITbWorkdayService;
import com.markmzy.mywork.wx.model.TbWorkday;
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
@RequestMapping("/tb-workday")
public class TbWorkdayController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbWorkdayService tbWorkdayService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody TbWorkday tbWorkday){
        return tbWorkdayService.add(tbWorkday);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbWorkdayService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody TbWorkday tbWorkday){
        return tbWorkdayService.updateData(tbWorkday);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbWorkday> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbWorkdayService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public TbWorkday findById(@PathVariable Long id){
        return tbWorkdayService.findById(id);
    }

}

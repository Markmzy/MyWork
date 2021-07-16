package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbCity;
import com.markmzy.mywork.wx.service.ITbCityService;
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
 * 疫情城市列表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {"疫情城市列表"})
@RestController
@RequestMapping("/tb-city")
public class TbCityController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbCityService tbCityService;


    @ApiOperation(value = "新增疫情城市列表")
    @PostMapping()
    public int add(@RequestBody TbCity tbCity)
    {
        return tbCityService.add(tbCity);
    }

    @ApiOperation(value = "删除疫情城市列表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbCityService.delete(id);
    }

    @ApiOperation(value = "更新疫情城市列表")
    @PutMapping()
    public int update(@RequestBody TbCity tbCity)
    {
        return tbCityService.updateData(tbCity);
    }

    @ApiOperation(value = "查询疫情城市列表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbCity> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount)
    {
        return tbCityService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询疫情城市列表")
    @GetMapping("{id}")
    public TbCity findById(@PathVariable Long id)
    {
        return tbCityService.findById(id);
    }

}

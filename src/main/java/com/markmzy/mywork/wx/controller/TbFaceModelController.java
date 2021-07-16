package com.markmzy.mywork.wx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.markmzy.mywork.wx.model.TbFaceModel;
import com.markmzy.mywork.wx.service.ITbFaceModelService;
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
 * 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Api(tags = {""})
@RestController
@RequestMapping("/tb-face-model")
public class TbFaceModelController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbFaceModelService tbFaceModelService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody TbFaceModel tbFaceModel)
    {
        return tbFaceModelService.add(tbFaceModel);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id)
    {
        return tbFaceModelService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody TbFaceModel tbFaceModel)
    {
        return tbFaceModelService.updateData(tbFaceModel);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbFaceModel> findListByPage(@RequestParam Integer page,
                                             @RequestParam Integer pageCount)
    {
        return tbFaceModelService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public TbFaceModel findById(@PathVariable Long id)
    {
        return tbFaceModelService.findById(id);
    }

}

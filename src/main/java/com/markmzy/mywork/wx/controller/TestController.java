package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.controller.form.TestSayHelloForm;
import com.markmzy.mywork.wx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @title: TestController
 * @Author Zhiyue Ma
 * @Date: 7/18/21 17:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = {"测试"})
public class TestController
{
    @PostMapping("/sayHello")
    @ApiOperation("测试方法")
    public R sayHello(@Valid @RequestBody TestSayHelloForm form)
    {
        return R.ok().put("message", "Hello," + form.getName());
    }
}

package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.service.ISysConfigService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统设置表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"系统设置表"})
@RestController
@RequestMapping("/sys-config")
public class SysConfigController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysConfigService sysConfigService;

}

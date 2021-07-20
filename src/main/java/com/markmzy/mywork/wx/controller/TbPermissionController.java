package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.service.ITbPermissionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"权限表"})
@RestController
@RequestMapping("/tb-permission")
public class TbPermissionController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbPermissionService tbPermissionService;

}

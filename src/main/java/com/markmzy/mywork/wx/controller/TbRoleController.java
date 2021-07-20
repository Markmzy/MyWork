package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.service.ITbRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"角色表"})
@RestController
@RequestMapping("/tb-role")
public class TbRoleController
{

    @Resource
    private ITbRoleService tbRoleService;

}

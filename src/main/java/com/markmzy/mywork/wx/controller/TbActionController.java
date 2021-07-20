package com.markmzy.mywork.wx.controller;

import com.markmzy.mywork.wx.service.ITbActionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 行为表 前端控制器
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Api(tags = {"行为表"})
@RestController
@RequestMapping("/tb-action")
public class TbActionController
{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbActionService tbActionService;


}

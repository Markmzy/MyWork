package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @title: LoginForm
 * @Author Zhiyue Ma
 * @Date: 7/20/21 09:47
 * @Version 1.0
 */
@ApiModel
@Data
public class LoginForm
{
    @NotBlank(message = "临时授权不能为空")
    @ApiModelProperty("临时授权")
    private String code;
}

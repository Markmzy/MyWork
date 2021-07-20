package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @title: RegisterForm
 * @Author Zhiyue Ma
 * @Date: 7/19/21 18:52
 * @Version 1.0
 */
@ApiModel
@Data
public class RegisterForm
{
    @NotBlank(message = "注册码不能为空")
    @Pattern(regexp = "^[0-9]{8}$",message = "注册码必须是8位数字")
    private String registerCode;

    @NotBlank(message = "微信临时授权不能为空")
    private String code;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "头像不能为空")
    private String photo;
}

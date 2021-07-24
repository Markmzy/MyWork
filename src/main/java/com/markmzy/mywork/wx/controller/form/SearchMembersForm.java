package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @title: SearchMembersForm
 * @Author Zhiyue Ma
 * @Date: 2021/7/26 09:42
 * @Version 1.0
 */
@Data
@ApiModel
public class SearchMembersForm
{
    @NotBlank(message = "成员不能为空")
    private String members;
}

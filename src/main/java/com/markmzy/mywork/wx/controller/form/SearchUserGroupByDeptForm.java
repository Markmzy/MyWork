package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @title: SearchUserGroupByDeptForm
 * @Author Zhiyue Ma
 * @Date: 7/24/21 15:45
 * @Version 1.0
 */
@Data
@ApiModel
public class SearchUserGroupByDeptForm
{
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,15}$")
    private String keyword;
}

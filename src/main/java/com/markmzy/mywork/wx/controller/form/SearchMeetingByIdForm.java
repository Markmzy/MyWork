package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @title: SearchMeetingByIdForm
 * @Author Zhiyue Ma
 * @Date: 2021/7/26 17:21
 * @Version 1.0
 */
@Data
@ApiModel
public class SearchMeetingByIdForm
{
    @NotNull(message = "会议id不能为空")
    @Min(value = 1,message = "会议id最小为1")
    private int id;
}

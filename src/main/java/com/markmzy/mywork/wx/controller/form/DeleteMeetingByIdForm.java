package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @title: DeleteMeetingByIdForm
 * @Author Zhiyue Ma
 * @Date: 2021/7/26 18:32
 * @Version 1.0
 */
@Data
@ApiModel
public class DeleteMeetingByIdForm
{
    @NotNull(message = "会议id不能为空")
    private Integer id;
}

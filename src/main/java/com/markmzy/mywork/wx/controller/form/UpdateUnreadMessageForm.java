package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateUnreadMessageForm
{
    @NotNull(message = "消息id不能为空")
    private String id;
}

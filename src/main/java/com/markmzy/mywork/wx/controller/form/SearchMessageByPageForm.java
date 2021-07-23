package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @title: SearchMessageByPageForm
 * @Author Zhiyue Ma
 * @Date: 7/22/21 19:51
 * @Version 1.0
 */

@ApiModel
@Data
public class SearchMessageByPageForm
{
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页面最小是1")
    private long pageNum;

    @NotNull(message = "页数据量不能为空")
    @Range(min = 1, max = 40, message = "页数据量范围是1-40")
    private int pageSize;
}

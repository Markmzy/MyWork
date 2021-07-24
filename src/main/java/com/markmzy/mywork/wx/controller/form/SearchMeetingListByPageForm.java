package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @title: SearchMeetingListByPageForm
 * @Author Zhiyue Ma
 * @Date: 7/24/21 12:29
 * @Version 1.0
 */

@Data
@ApiModel
public class SearchMeetingListByPageForm
{
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Integer pageNum;

    @NotNull(message = "页数据量不能为空")
    @Range(min = 1, max = 40, message = "页数据量范围从1到40")
    private Integer pageSize;
}

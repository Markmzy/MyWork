package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @title: SearchMonthCheckInForm
 * @Author Zhiyue Ma
 * @Date: 7/22/21 14:04
 * @Version 1.0
 */
@ApiModel
@Data
public class SearchMonthCheckInForm
{
    @NotNull
    @Range(min = 1900, max = 3000)
    @ApiModelProperty("年份")
    private Integer year;

    @NotNull
    @Range(min = 1, max = 12)
    @ApiModelProperty("月份")
    private Integer month;

}

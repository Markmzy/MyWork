package com.markmzy.mywork.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: CheckinForm
 * @Author Zhiyue Ma
 * @Date: 7/21/21 10:42
 * @Version 1.0
 */

@ApiModel
@Data
public class CheckinForm
{
    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String district;

}

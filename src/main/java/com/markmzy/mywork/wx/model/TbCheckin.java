package com.markmzy.mywork.wx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 签到表
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbCheckin对象", description="签到表")
public class TbCheckin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "签到地址")
    private String address;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区划")
    private String district;

    @ApiModelProperty(value = "考勤结果")
    private Integer status;

    @ApiModelProperty(value = "风险等级")
    private Integer risk;

    @ApiModelProperty(value = "签到日期")
    private LocalDate date;

    @ApiModelProperty(value = "签到时间")
    private Date createTime;


}

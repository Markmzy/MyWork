package com.markmzy.mywork.wx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 * 会议表
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TbMeeting对象", description = "会议表")
public class TbMeeting implements Serializable
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "UUID")
    private String uuid;

    @ApiModelProperty(value = "会议题目")
    private String title;

    @ApiModelProperty(value = "创建人ID")
    private Long creatorId;

    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "开会地点")
    private String place;

    @ApiModelProperty(value = "开始时间")
    private LocalTime start;

    @ApiModelProperty(value = "结束时间")
    private LocalTime end;

    @ApiModelProperty(value = "会议类型（1在线会议，2线下会议）")
    private Integer type;

    @ApiModelProperty(value = "参与者")
    private String members;

    @ApiModelProperty(value = "会议内容")
    private String desc;

    @ApiModelProperty(value = "工作流实例ID")
    private String instanceId;

    @ApiModelProperty(value = "状态（1未开始，2进行中，3已结束）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

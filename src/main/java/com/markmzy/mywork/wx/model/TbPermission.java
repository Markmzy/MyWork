package com.markmzy.mywork.wx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Zhiyue Ma
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TbPermission对象", description = "权限表")
public class TbPermission implements Serializable
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限")
    private String permissionName;

    @ApiModelProperty(value = "模块ID")
    private Integer moduleId;

    @ApiModelProperty(value = "行为ID")
    private Integer actionId;


}

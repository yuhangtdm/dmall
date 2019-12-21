package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性表
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeDTO", description = "属性表")
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "属性分类id,可以为null", position = 2)
    private Long attributeTypeId;

    @ApiModelProperty(value = "名称", position = 3)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 4)
    private String showName;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 5)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 6)
    private String inputList;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 7)
    private String handAddStatus;

    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 12)
    private String isDeleted;

}

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
 * @author: created by hang.yu on 2019-12-02 23:55:34
 */
@Data
@Accessors(chain = true)
@ApiModel(value="AttributeDTO", description="属性表")
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "属性分类id", position = 2)
    private Long attributeCategoryId;

    @ApiModelProperty(value = "商品分类id", position = 3)
    private Long categoryId;

    @ApiModelProperty(value = "商品分类id集合 如，1/2/3", position = 4)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "名称", position = 5)
    private String name;

    @ApiModelProperty(value = "备注", position = 6)
    private String remark;

    @ApiModelProperty(value = "类型 1-规格;2-参数;", position = 7)
    private Integer type;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 8)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 9)
    private String inputList;

    @ApiModelProperty(value = "属性选择类型 1-单选;2-多选", position = 10)
    private String selectType;

    @ApiModelProperty(value = "排序", position = 11)
    private Integer sort;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 12)
    private String handAddStatus;

    @ApiModelProperty(value = "属性值数量", position = 13)
    private Integer valueCount;

    @ApiModelProperty(value = "创建人", position = 14)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 15)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 16)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 17)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 18)
    private String isDeleted;

}

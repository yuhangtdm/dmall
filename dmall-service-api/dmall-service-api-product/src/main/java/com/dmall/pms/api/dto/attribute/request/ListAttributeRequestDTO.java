package com.dmall.pms.api.dto.attribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 属性列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListAttributeRequestDTO", description="属性列表请求实体")
public class ListAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




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












}

package com.dmall.pms.api.dto.attribute.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description: 商品属性分页响应实体
 * @author: created by hang.yu on 2019/12/24 22:28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeResponseDTO", description = "商品属性响应实体")
public class AttributeResponseDTO {

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 3)
    private String showName;

    @ApiModelProperty(value = "类型", position = 4)
    private Integer type;

    @ApiModelProperty(value = "属性录入方式", position = 5)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 6)
    private String inputList;

    @ApiModelProperty(value = "是否支持手动新增", position = 7)
    private String handAddStatus;

    @ApiModelProperty(value = "创建人", position = 9)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 11)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 13)
    private String isDeleted;

    @ApiModelProperty(value = "一级商品分类", position = 14)
    private Long categoryId;

    @ApiModelProperty(value = "是否选中", position = 15)
    @JsonProperty(value = "LAY_CHECKED")
    private Boolean checked;

    @ApiModelProperty(value = "是否可筛选", position = 16)
    private Integer canScreen;

}

package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @description: 属性列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@ApiModel(value = "ListAttributeRequestDTO", description = "商品属性列表请求实体")
public class ListAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "一级分类id", required = true, position = 1)
    private Long categoryId;

    @ApiModelProperty(value = "展示名称", position = 2)
    private String showName;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 3)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 4)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

    @ApiModelProperty(value = "三级分类id", required = true, position = 5)
    private Set<Long> threeCategoryIds;

}

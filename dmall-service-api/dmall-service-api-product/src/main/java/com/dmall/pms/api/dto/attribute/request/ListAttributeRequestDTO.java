package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.TypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 属性列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@ApiModel(value = "ListAttributeRequestDTO", description = "商品属性列表请求实体")
public class ListAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "一级分类id", required = true, position = 1)
    @NotNull(message = "一级分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "展示名称", position = 2)
    private String showName;

    @ApiModelProperty(value = "属性类型 1-普通属性;2-公共属性", position = 3)
    @ValueInEnum(TypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 4)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 5)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

}

package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@ApiModel(value = "UpdateAttributeRequestDTO", description = "修改商品属性请求实体")
public class UpdateAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = -3857293667290459220L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "展示名称", required = true, position = 2)
    @NotBlank(message = "展示名称不能为空")
    private String showName;


    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 3)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表", position = 4)
    private String inputList;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 5)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

}

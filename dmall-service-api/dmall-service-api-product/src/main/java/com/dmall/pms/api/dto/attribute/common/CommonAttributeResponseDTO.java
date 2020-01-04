package com.dmall.pms.api.dto.attribute.common;

import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 属性公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonAttributeResponseDTO" , description = "属性公共响应实体" )
public class CommonAttributeResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id" , position = 1)
    private Long id;

    @ApiModelProperty(value = "名称" , position = 2)
    private String name;

    @ApiModelProperty(value = "展示名称" , position = 3)
    private String showName;

    @ApiModelProperty(value = "类型" , position = 4)
    private TypeEnum type;

    @ApiModelProperty(value = "属性录入方式" , position = 5)
    private InputTypeEnum inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开" , position = 6)
    private String inputList;

    @ApiModelProperty(value = "是否支持手动新增" , position = 7)
    private HandAddStatusEnum handAddStatus;


}

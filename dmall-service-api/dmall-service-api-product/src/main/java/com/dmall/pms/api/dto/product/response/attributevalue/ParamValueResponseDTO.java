package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 参数值响应实体
 * @author: created by hang.yu on 2019/12/26 22:46
 */
@Data
@ApiModel(value = "ParamValueResponseDTO", description = "参数值响应实体")
public class ParamValueResponseDTO implements Serializable {

    private static final long serialVersionUID = 2436674140152898894L;

    @ApiModelProperty(value = "属性id", position = 1)
    private Long attributeId;

    @ApiModelProperty(value = "商品属性值列表", position = 2)
    private List<ProductAttributeValueResponseDTO> paramValues;

}

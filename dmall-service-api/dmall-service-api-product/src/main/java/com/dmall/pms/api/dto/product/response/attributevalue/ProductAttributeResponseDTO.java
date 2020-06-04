package com.dmall.pms.api.dto.product.response.attributevalue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品属性响应实体
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@ApiModel(value = "ProductAttributeResponseDTO", description = "销售规格响应实体")
public class ProductAttributeResponseDTO implements Serializable {

    private static final long serialVersionUID = -4713638581805218367L;

    @ApiModelProperty(value = "属性id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long attributeId;

    @ApiModelProperty(value = "属性名称", position = 2)
    private String attributeName;

    @ApiModelProperty(value = "商品属性值列表", position = 3)
    private List<ProductAttributeValueResponseDTO> attributeValues;

}

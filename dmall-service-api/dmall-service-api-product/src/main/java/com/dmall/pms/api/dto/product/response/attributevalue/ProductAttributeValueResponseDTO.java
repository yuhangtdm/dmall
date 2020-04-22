package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 商品属性值响应实体
 * @author: created by hang.yu on 2019/12/27 21:40
 */
@Data
@ApiModel(value = "ProductAttributeValueResponseDTO", description = "商品属性值响应实体")
public class ProductAttributeValueResponseDTO implements Serializable {

    private static final long serialVersionUID = -4666810266301095465L;

    @ApiModelProperty(value = "商品属性值id", position = 1)
    private Long productAttributeValueId;

    @ApiModelProperty(value = "属性值", position = 1)
    private String attributeValue;

    @ApiModelProperty(value = "属性值配图", position = 2)
    private String pic;
}

package com.dmall.pms.api.dto.product.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品属性响应实体
 * @author: created by hang.yu on 2019/12/16 12:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeDTO", description = "商品属性响应实体")
public class ProductAttributeDTO implements Serializable {

    @ApiModelProperty(value = "属性id", position = 1)
    private Long attributeId;

    @ApiModelProperty(value = "属性名称", position = 2)
    private String attributeName;

    @ApiModelProperty(value = "属性值列表", position = 3)
    private List<ProductAttributeValueDTO> attributeValues;

}

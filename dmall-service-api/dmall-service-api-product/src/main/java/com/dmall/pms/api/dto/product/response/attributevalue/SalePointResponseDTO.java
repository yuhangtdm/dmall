package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 卖点响应实体
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SalePointResponseDTO" , description = "卖点响应实体" )
public class SalePointResponseDTO implements Serializable {

    private static final long serialVersionUID = 1441839800494180753L;

    @ApiModelProperty(value = "属性id" , position = 1)
    private Long attributeId;

    @ApiModelProperty(value = "商品属性值列表" , position = 2)
    private List<ProductAttributeValueResponseDTO> salePointValues;

}

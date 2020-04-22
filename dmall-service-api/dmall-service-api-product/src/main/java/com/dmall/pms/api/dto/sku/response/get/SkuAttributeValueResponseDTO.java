package com.dmall.pms.api.dto.sku.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: sku属性值响应实体
 * @author: created by hang.yu on 2019/12/29 16:29
 */
@Data
@ApiModel(value = "SkuAttributeValueResponseDTO", description = "sku属性值响应实体")
public class SkuAttributeValueResponseDTO implements Serializable {

    private static final long serialVersionUID = -3574763781998162025L;

    @ApiModelProperty(value = "商品分类id列表", position = 1)
    private List<Long> categoryIds;

    @ApiModelProperty(value = "品牌id", position = 2)
    private Long brandId;

    @ApiModelProperty(value = "商品属性值id列表", position = 3)
    private List<Long> productAttributeValueList;
}

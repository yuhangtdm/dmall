package com.dmall.pms.api.dto.product.response.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品扩展信息
 * @author: created by hang.yu on 2019/12/10 22:08
 */
@Data
@ApiModel(value = "ProductExtResponseDTO", description = "商品响应扩展信息")
public class ProductExtResponseDTO implements Serializable {

    private static final long serialVersionUID = -4383222923230954323L;

    @ApiModelProperty(value = "商品分类id列表", position = 1)
    private List<Long> categoryIds;

    @ApiModelProperty(value = "商品分类名称列表", position = 2)
    private List<String> cascadeCategoryNames;

    @ApiModelProperty(value = "品牌id", position = 3)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称", position = 4)
    private String brandName;

    @ApiModelProperty(value = "销售规格", position = 5)
    private List<ProductAttributeResponseDTO> specifications;

    @ApiModelProperty(value = "卖点", position = 6)
    private List<ProductAttributeResponseDTO> salePoints;

    @ApiModelProperty(value = "参数", position = 7)
    private List<ParamResponseDTO> params;

}

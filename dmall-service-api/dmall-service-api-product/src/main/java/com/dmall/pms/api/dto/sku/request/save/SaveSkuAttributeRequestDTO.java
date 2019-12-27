package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 新增sku属性实体
 * @author: created by hang.yu on 2019/12/16 16:39
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveSkuAttributeRequestDTO", description = "新增sku属性实体")
public class SaveSkuAttributeRequestDTO {

    @ApiModelProperty(value = "商品分类id", position = 1)
    @NotNull(message = "商品分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 2)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "属性值id", position = 3)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "商品属性值id列表", position = 4)
    @NotNull(message = "属性值id不能为空")
    private List<Long> attributeValueList;
}
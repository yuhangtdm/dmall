package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增sku属性实体
 * @author: created by hang.yu on 2019/12/16 16:39
 */
@Data
@ApiModel(value = "SaveSkuAttributeRequestDTO", description = "新增sku属性实体")
public class SaveSkuAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = -6150027620505333799L;

    @ApiModelProperty(value = "商品id", required = true, position = 1)
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "属性值id", required = true, position = 2)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "商品属性值id列表", required = true, position = 3)
    @NotNull(message = "商品属性值id不能为空")
    private List<Long> productAttributeValueList;
}

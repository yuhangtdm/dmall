package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品扩展信息
 * @author: created by hang.yu on 2019/12/10 22:08
 */
@Data
@ApiModel(value = "ProductExtRequestDTO", description = "商品请求扩展信息")
public class ProductExtRequestDTO implements Serializable {

    private static final long serialVersionUID = -4383222923230954323L;

    @ApiModelProperty(value = "商品分类id列表,默认第一个为主分类", required = true, position = 1)
    @NotNull(message = "商品分类id列表不能为空")
    @Size(min = 1, message = "商品分类id列表不能为空")
    private List<Long> categoryIds;

    @ApiModelProperty(value = "品牌id", required = true, position = 2)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "商品请求属性信息", required = true, position = 3)
    @Valid
    @NotNull(message = "商品请求属性信息不能为空")
    private ProductAttributeRequestDTO productAttribute;

}

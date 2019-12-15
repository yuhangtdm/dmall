package com.dmall.pms.api.dto.product.request.save;

import com.dmall.pms.api.dto.product.request.attribute.AttributeTypeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 商品属性实体
 * @author: created by hang.yu on 2019/12/10 22:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeDTO", description = "商品属性实体")
public class ProductAttributeDTO implements Serializable {

    private static final long serialVersionUID = -4383222923230954323L;

    @ApiModelProperty(value = "商品分类id", position = 1)
    @NotNull(message = "商品分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 2)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "销售规格", position = 3)
    @Valid
    @NotNull(message = "销售规格不能为空")
    private AttributeTypeDTO specifications;

    @ApiModelProperty(value = "销售参数", position = 4)
    @Valid
    @NotNull(message = "销售参数不能为空")
    private List<AttributeTypeDTO> params;

}

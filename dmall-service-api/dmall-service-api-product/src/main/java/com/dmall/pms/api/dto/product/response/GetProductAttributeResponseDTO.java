package com.dmall.pms.api.dto.product.response;

import com.dmall.pms.api.dto.product.response.get.ProductAttributeTypeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: 查询商品属性实体
 * @author: created by hang.yu on 2019/12/16 16:22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "GetProductResponseDTO", description = "查询商品属性实体")
public class GetProductAttributeResponseDTO {

    @ApiModelProperty(value = "销售规格", position = 1)
    private ProductAttributeTypeDTO specifications;

    @ApiModelProperty(value = "销售参数", position = 2)
    private List<ProductAttributeTypeDTO> params;
}

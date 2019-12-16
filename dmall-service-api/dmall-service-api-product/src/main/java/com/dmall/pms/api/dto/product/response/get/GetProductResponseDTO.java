package com.dmall.pms.api.dto.product.response.get;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 查询商品响应实体
 * @author: created by hang.yu on 2019/12/16 11:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "GetProductResponseDTO", description = "查询商品响应实体")
public class GetProductResponseDTO implements Serializable {

    private static final long serialVersionUID = -7462493897400340534L;

    @ApiModelProperty(value = "商品基本信息", position = 1)
    private BasicProductResponseDTO basicProduct;

    @ApiModelProperty(value = "销售规格", position = 2)
    private ProductAttributeTypeDTO specifications;

    @ApiModelProperty(value = "销售参数", position = 3)
    private List<ProductAttributeTypeDTO> params;

}

package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: sku实体
 * @author: created by hang.yu on 2019/12/26 22:54
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AddSkuRequestDTO" , description = "sku实体" )
public class AddSkuRequestDTO implements Serializable {

    private static final long serialVersionUID = 2864165329870133989L;

    @ApiModelProperty(value = "sku规格列表" , required = true, position = 1)
    @Valid
    @NotNull(message = "sku规格列表不能为空" )
    @Size(min = 1, message = "sku规格列表不能为空" )
    private List<SkuSpecificationsRequestDTO> skuSpecifications;

    @ApiModelProperty(value = "价格" , required = true, position = 2)
    @NotNull(message = "价格不能为空" )
    private BigDecimal price;

    @ApiModelProperty(value = "库存" , required = true, position = 3)
    @NotNull(message = "库存不能为空" )
    private Integer stock;

}

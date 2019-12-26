package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: sku实体
 * @author: created by hang.yu on 2019/12/26 22:54
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SkuDTO", description = "sku实体")
public class SkuDTO implements Serializable {

    private static final long serialVersionUID = 2864165329870133989L;

    @ApiModelProperty(value = "sku规格列表", position = 1)
    @NotNull(message = "sku规格列表不能为空")
    private List<SkuSpecificationsDTO> skuSpecifications;

    @ApiModelProperty(value = "价格", position = 2)
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "库存", position = 3)
    @NotNull(message = "库存不能为空")
    private Integer stock;

}

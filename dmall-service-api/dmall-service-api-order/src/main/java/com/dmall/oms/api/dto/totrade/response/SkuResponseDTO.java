package com.dmall.oms.api.dto.totrade.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: sku实体
 * @author: created by hang.yu on 2020/3/26 22:45
 */
@Data
@ApiModel(value = "SkuResponseDTO", description = "sku实体")
public class SkuResponseDTO implements Serializable {

    private static final long serialVersionUID = -5291869754931654358L;

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku规格", position = 3)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "添加的数量", position = 4)
    private Integer number;

    @ApiModelProperty(value = "sku单价", position = 5)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku总价", position = 6)
    private BigDecimal skuTotalPrice;

    @ApiModelProperty(value = "是否有货", position = 7)
    private Boolean hasStock;

    @ApiModelProperty(value = "重量", position = 8)
    private String weight;
}

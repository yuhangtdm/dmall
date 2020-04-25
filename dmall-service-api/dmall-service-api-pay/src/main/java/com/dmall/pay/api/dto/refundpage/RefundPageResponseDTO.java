package com.dmall.pay.api.dto.refundpage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 退款明细分页响应实体
 * @author: created by hang.yu on 2020/4/16 23:10
 */
@Data
@ApiModel(value = "RefundPageResponseDTO", description = "退款明细分页响应实体")
public class RefundPageResponseDTO implements Serializable {

    private static final long serialVersionUID = 8981035676577072486L;

    @ApiModelProperty(value = "售后单号", position = 1)
    private Long afterSaleId;

    @ApiModelProperty(value = "订单号", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "skuId", position = 3)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 4)
    private String skuName;

    @ApiModelProperty(value = "sku数量", position = 5)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku名称", position = 6)
    private BigDecimal skuToTalPrice;

}

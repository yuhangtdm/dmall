package com.dmall.oms.api.dto.items;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单明细响应实体
 * @author: created by hang.yu on 2020/4/5 10:11
 */
@Data
@ApiModel(value = "OrderItemListResponseDTO", description = "订单明细响应实体")
public class OrderItemListResponseDTO {

    @ApiModelProperty(value = "订单项id", position = 1)
    private Long id;

    @ApiModelProperty(value = "订单号", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "skuId", position = 3)
    private Long skuId;

    @ApiModelProperty(value = "商品id", position = 4)
    private Long productId;

    @ApiModelProperty(value = "sku成交数量", position = 5)
    private Integer skuNumber;

}

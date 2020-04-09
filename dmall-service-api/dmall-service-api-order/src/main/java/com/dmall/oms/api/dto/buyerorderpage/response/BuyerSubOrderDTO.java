package com.dmall.oms.api.dto.buyerorderpage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 买家端子订单列表响应实体
 * @author: created by hang.yu on 2020/4/9 22:26
 */
@Data
@ApiModel(value = "BuyerSubOrderDTO", description = "买家端子订单列表响应实体")
public class BuyerSubOrderDTO {

    @ApiModelProperty(value = "子订单id", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "sku信息", position = 2)
    private BuyerSkuDTO sku;
}

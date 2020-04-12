package com.dmall.oms.api.dto.buyerorderpage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 买家端子订单列表响应实体
 * @author: created by hang.yu on 2020/4/9 22:26
 */
@Data
@ApiModel(value = "BuyerSubOrderDTO", description = "买家端子订单列表响应实体")
public class BuyerSubOrderDTO {

    @ApiModelProperty(value = "子订单id", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "订单的sku列表", position = 2)
    private List<BuyerSkuDTO> skuList;
}

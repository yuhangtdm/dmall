package com.dmall.oms.service.impl.order.es;

import lombok.Data;

import java.util.List;

/**
 * @description: 子订单
 * @author: created by hang.yu on 2020/4/4 15:18
 */
@Data
public class SubOrderDTO {

    /**
     * 子订单id
     */
    private Long subOrderId;

    /**
     * 订单的sku列表
     */
    private List<SkuDTO> skuList;
}

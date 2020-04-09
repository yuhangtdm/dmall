package com.dmall.oms.service.impl.order.es;

import lombok.Data;

import java.math.BigDecimal;

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
     * skuId
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku主图
     */
    private String skuMainPic;

    /**
     * sku数量
     */
    private Integer skuNumber;

    /**
     * sku支付总价
     */
    private BigDecimal skuTotalPrice;
}

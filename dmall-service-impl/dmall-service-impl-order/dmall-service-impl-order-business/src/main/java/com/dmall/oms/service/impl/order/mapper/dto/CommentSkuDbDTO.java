package com.dmall.oms.service.impl.order.mapper.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 评价订单商品响应实体
 * @author: created by hang.yu on 2020/4/9 22:34
 */
@Data
public class CommentSkuDbDTO {

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

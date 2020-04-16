package com.dmall.oms.service.impl.order.mapper.dto;

import lombok.Data;

/**
 * @description: MyAfterSalePageDbDTO
 * @author: created by hang.yu on 2020/4/16 23:01
 */
@Data
public class MyAfterSalePageDbDTO {

    /**
     * 售后单号
     */
    private Long afterSaleId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 售后状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long creator;
}

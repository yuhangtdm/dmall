package com.dmall.oms.service.impl.order.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 订单es对象
 * @author: created by hang.yu on 2020/4/4 15:14
 */
@Data
public class OrderEsDTO {

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 订单创建人
     */
    private Long creator;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 支付金额
     */
    private BigDecimal paymentPrice;

    /**
     * 订单来源
     */
    private Integer source;

    /**
     * 支付状态
     */
    private Integer paymentType;

    /**
     * 拆单情况
     */
    private Integer split;

    /**
     * 下单时间
     */
    private Date orderTime;



    /**
     * 子订单列表
     */
    private List<SubOrderDTO> subOrderList;

    /**
     * 收货人信息
     */
    private ReceiverDTO receiver;
}

package com.dmall.oms.service.impl.order.es;

import java.util.Date;
import java.util.List;

/**
 * @description: 订单es对象
 * @author: created by hang.yu on 2020/4/4 15:14
 */
public class OrderEsDTO {

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 下单时间
     */
    private Date orderTime;


    private List<SubOrderDTO> subOrderList;
}

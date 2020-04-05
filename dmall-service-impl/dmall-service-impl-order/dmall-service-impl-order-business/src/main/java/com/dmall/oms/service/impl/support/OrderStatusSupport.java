package com.dmall.oms.service.impl.support;

import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: OrderStatusSupport
 * @author: created by hang.yu on 2020/4/5 21:10
 */
@Component
public class OrderStatusSupport {

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    /**
     * 插入记录状态
     */
    public void  insert(Long orderId, Integer orderStatus){
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setOrderId(orderId);
        orderStatusDO.setOrderStatus(orderStatus);
        orderStatusMapper.insert(orderStatusDO);
    }
}

package com.dmall.oms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void insert(Long orderId, Integer orderStatus) {
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setOrderId(orderId);
        orderStatusDO.setOrderStatus(orderStatus);
        orderStatusMapper.insert(orderStatusDO);
    }

    /**
     * 根据订单号查询列表
     */
    public List<OrderStatusDO> listByOrderId(Long orderId) {
        return orderStatusMapper.selectList(Wrappers.<OrderStatusDO>lambdaQuery()
            .eq(OrderStatusDO::getOrderId, orderId)
            .orderByAsc(OrderStatusDO::getGmtCreated));
    }
}

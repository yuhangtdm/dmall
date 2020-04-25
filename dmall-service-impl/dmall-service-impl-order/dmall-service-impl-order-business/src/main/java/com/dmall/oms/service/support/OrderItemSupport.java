package com.dmall.oms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: OrderItemSupport
 * @author: created by hang.yu on 2020/4/6 11:17
 */
@Component
public class OrderItemSupport {

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 根据订单号查询
     */
    public List<OrderItemDO> listByOrderId(Long orderId) {
        return orderItemMapper.selectList(Wrappers.lambdaQuery(new OrderItemDO()).eq(OrderItemDO::getOrderId, orderId));
    }

    /**
     * 根据子订单号查询
     */
    public List<OrderItemDO> listBySubOrderId(Long subOrderId) {
        return orderItemMapper.selectList(Wrappers.lambdaQuery(new OrderItemDO()).eq(OrderItemDO::getSubOrderId, subOrderId));
    }

    /**
     * 根据订单号和skuId查询
     */
    public OrderItemDO findByOrderIdAndSkuId(Long orderId, Long skuId) {
        return orderItemMapper.selectOne(Wrappers.lambdaQuery(new OrderItemDO())
                .eq(OrderItemDO::getOrderId, orderId)
                .eq(OrderItemDO::getSkuId, skuId)
        );
    }
}

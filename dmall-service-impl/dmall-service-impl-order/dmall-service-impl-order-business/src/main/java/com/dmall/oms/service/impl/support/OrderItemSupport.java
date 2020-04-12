package com.dmall.oms.service.impl.support;

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

    public List<OrderItemDO> listByOrderId(Long orderId){
        return orderItemMapper.selectList(Wrappers.<OrderItemDO>lambdaQuery().eq(OrderItemDO::getOrderId, orderId));
    }

    public OrderItemDO findByOrderIdAndSkuId(Long orderId, Long skuId){
        return orderItemMapper.selectOne(Wrappers.<OrderItemDO>lambdaQuery()
                .eq(OrderItemDO::getOrderId, orderId)
                .eq(OrderItemDO::getSkuId, skuId)
        );
    }
}

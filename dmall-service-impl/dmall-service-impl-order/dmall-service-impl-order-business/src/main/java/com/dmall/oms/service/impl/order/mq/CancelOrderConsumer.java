package com.dmall.oms.service.impl.order.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.impl.order.handler.CommonStockHandler;
import com.dmall.oms.service.support.OrderLogSupport;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description: 订单支付成功的监听器
 * @author: created by hang.yu on 2020/3/25 23:31
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MqConstants.DELAY_CANCEL_ORDER_TOPIC, consumerGroup = OrderConstants.DELAY_CANCEL_ORDER_CONSUMER)
public class CancelOrderConsumer implements RocketMQListener<Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CommonStockHandler commonStockHandler;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private OrderLogSupport orderLogSupport;

    private static final String LOG_CONTENT = "超时未支付,自动取消订单";

    /**
     * 订单超时未支付 取消订单 释放锁定的库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onMessage(Long orderId) {
        log.info("receive delay cancel order message,orderId:{}", orderId);
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return;
        }
        // 如果订单状态还是待支付 则取消订单,释放库存
        if (OrderStatusEnum.WAIT_PAY.getCode().equals(orderDO.getStatus())) {
            orderDO.setStatus(OrderStatusEnum.CANCELED.getCode());
            orderDO.setCancelTime(new Date());
            orderDO.setCancelType(CancelTypeEnum.AUTO.getCode());
            orderMapper.updateById(orderDO);
            orderLogSupport.insert(orderId, OrderOperateEnum.CANCEL, true, null, LOG_CONTENT);
            StockRequestDTO stockRequestDTO = commonStockHandler.build(orderId);
            BaseResult<Void> lockStock = skuFeign.unLockStock(stockRequestDTO);
            if (!lockStock.getResult()) {
                throw new BusinessException();
            }
        }
    }
}

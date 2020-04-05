package com.dmall.oms.service.impl.order.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.PayStatusEnum;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
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
@RocketMQMessageListener(topic = MqConstants.PAY_SUCCESS_TOPIC, consumerGroup = OrderConstants.CONSUMER_GROUP)
public class PaySuccessConsumer implements RocketMQListener<Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CommonStockHandler commonStockHandler;

    @Autowired
    private SkuFeign skuFeign;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onMessage(Long orderId) {
        log.info("receive pay success message,orderId:{}", orderId);
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return;
        }
        if (!OrderStatusEnum.WAIT_PAY.getCode().equals(orderDO.getStatus())) {
            return;
        }
        // 修改订单的状态、支付状态、支付时间
        orderDO.setStatus(OrderStatusEnum.WAIT_SHIP.getCode());
        orderDO.setPaymentTime(new Date());
        orderDO.setPaymentStatus(PayStatusEnum.PAYED.getCode());
        orderMapper.updateById(orderDO);

        // 出库
        StockRequestDTO stockRequestDTO = commonStockHandler.build(orderId);
        BaseResult<Void> stockResult = skuFeign.outStock(stockRequestDTO);
        if (!stockResult.getResult()) {
            throw new BusinessException();
        }
        log.info("update order success, out stock success, orderId:{}", orderId);
    }
}

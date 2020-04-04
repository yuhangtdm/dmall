package com.dmall.pms.service.impl.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.oms.api.dto.listitem.OrderItemListResponseDTO;
import com.dmall.pms.feign.OrderFeign;
import com.dmall.pms.service.impl.ProductConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 订单支付成功的监听器
 * @author: created by hang.yu on 2020/3/25 23:31
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MqConstants.PAY_SUCCESS_TOPIC, consumerGroup = ProductConstants.CONSUMER_GROUP)
public class PaySuccessConsumer implements RocketMQListener<Long> {

    @Autowired
    private OrderFeign orderFeign;

    @Override
    public void onMessage(Long orderId) {
        log.info("receive pay success message,orderId:{}", orderId);
        BaseResult<List<OrderItemListResponseDTO>> listBaseResult = orderFeign.listItemByOrderId(orderId);
        if(!listBaseResult.getResult()){
           log.error("order system error,orderId:{}", orderId);
        }
        List<OrderItemListResponseDTO> data = listBaseResult.getData();
    }
}

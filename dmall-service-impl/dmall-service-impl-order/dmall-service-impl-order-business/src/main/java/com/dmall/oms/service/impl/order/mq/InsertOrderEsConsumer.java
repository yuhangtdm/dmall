package com.dmall.oms.service.impl.order.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.impl.order.es.*;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 同步订单到es的消费者
 * @author: created by hang.yu on 2020/4/8 23:28
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MqConstants.SYNC_ES_ORDER, consumerGroup = OrderConstants.CONSUMER_GROUP)
public class InsertOrderEsConsumer implements RocketMQListener<Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private ESDao esDao;

    @Override
    public void onMessage(Long orderId) {
        log.info("receive insert order es message,orderId:{}", orderId);
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return;
        }
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderId);
        esDao.saveOrUpdate(buildSkuEsDTO(orderDO, subOrderList), EsConstants.INDEX_NAME, EsConstants.TYPE_NAME, orderDO.getId());
    }

    /**
     * 构建订单的es实体
     */
    private OrderEsDTO buildSkuEsDTO(OrderDO orderDO, List<SubOrderDO> subOrderDOList) {
        OrderEsDTO orderEsDTO = new OrderEsDTO();
        orderEsDTO.setOrderId(orderDO.getId());
        orderEsDTO.setCreator(orderDO.getCreator());
        orderEsDTO.setOrderStatus(orderDO.getStatus());
        orderEsDTO.setPaymentPrice(orderDO.getPaymentPrice());
        orderEsDTO.setSource(orderDO.getSource());
        orderEsDTO.setPaymentType(orderDO.getPaymentType());
        orderEsDTO.setSplit(orderDO.getSplit());
        orderEsDTO.setOrderTime(orderDO.getGmtCreated());
        ReceiverDTO receiver = new ReceiverDTO();
        receiver.setReceiverName(orderDO.getReceiverName());
        receiver.setReceiverPhone(orderDO.getReceiverPhone());
        receiver.setReceiverAddress(orderDO.getReceiverPhone() + orderDO.getReceiverCity()
                + orderDO.getReceiverRegion() + orderDO.getReceiverDetailAddress());
        orderEsDTO.setReceiver(receiver);

        if (SplitEnum.NOT_NEED.getCode().equals(orderDO.getSplit())) {
            // 无需拆单
            List<SubOrderDTO> subOrderList = subOrderDOList.stream().map(subOrderDO -> {
                SubOrderDTO subOrderDTO = new SubOrderDTO();
                subOrderDTO.setSubOrderId(subOrderDO.getId());
                List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(subOrderDO.getOrderId());
                List<SkuDTO> skuList = buildSkuList(orderItemList);
                subOrderDTO.setSkuList(skuList);
                return subOrderDTO;
            }).collect(Collectors.toList());
            orderEsDTO.setSubOrderList(subOrderList);
        } else {
            List<SubOrderDTO> subOrderList = subOrderDOList.stream().map(subOrderDO -> {
                SubOrderDTO subOrderDTO = new SubOrderDTO();
                subOrderDTO.setSubOrderId(subOrderDO.getId());
                subOrderDTO.setStatus(subOrderDO.getStatus());
                List<OrderItemDO> orderItemList = orderItemSupport.listBySubOrderId(subOrderDO.getId());
                List<SkuDTO> skuList = buildSkuList(orderItemList);
                subOrderDTO.setSkuList(skuList);
                return subOrderDTO;
            }).collect(Collectors.toList());
            orderEsDTO.setSubOrderList(subOrderList);
        }
        return orderEsDTO;
    }

    private List<SkuDTO> buildSkuList(List<OrderItemDO> orderItemList) {
        return orderItemList.stream().map(orderItemDO -> {
            SkuDTO skuDTO = new SkuDTO();
            skuDTO.setSkuId(orderItemDO.getSkuId());
            skuDTO.setSkuName(orderItemDO.getSkuName());
            skuDTO.setSkuMainPic(orderItemDO.getSkuPic());
            skuDTO.setSkuNumber(orderItemDO.getSkuNumber());
            skuDTO.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
            return skuDTO;
        }).collect(Collectors.toList());
    }
}

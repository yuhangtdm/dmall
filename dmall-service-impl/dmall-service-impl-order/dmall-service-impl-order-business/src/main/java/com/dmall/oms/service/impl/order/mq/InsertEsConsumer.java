package com.dmall.oms.service.impl.order.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.impl.order.es.EsConstants;
import com.dmall.oms.service.impl.order.es.OrderEsDTO;
import com.dmall.oms.service.impl.order.es.SkuDTO;
import com.dmall.oms.service.impl.order.es.SubOrderDTO;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 同步订单到es的消费者
 * @author: created by hang.yu on 2020/4/8 23:28
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MqConstants.SYNC_ES_ORDER, consumerGroup = OrderConstants.CONSUMER_GROUP)
public class InsertEsConsumer implements RocketMQListener<Long> {

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
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderId);
        List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(orderId);
        Map<Long, OrderItemDO> orderItemMap = orderItemList.stream().collect(Collectors.toMap(OrderItemDO::getId, orderItemDO -> orderItemDO));
        esDao.saveOrUpdate(buildSkuEsDTO(orderDO, subOrderList, orderItemMap), EsConstants.INDEX_NAME,
                EsConstants.TYPE_NAME, orderDO.getId());
    }

    /**
     * 构建订单的es实体
     */
    private OrderEsDTO buildSkuEsDTO(OrderDO orderDO, List<SubOrderDO> subOrderDOList,
                                     Map<Long, OrderItemDO> orderItemMap) {
        OrderEsDTO orderEsDTO = new OrderEsDTO();
        orderEsDTO.setOrderId(orderDO.getId());
        orderEsDTO.setCreator(orderDO.getCreator());
        orderEsDTO.setOrderStatus(orderDO.getStatus());
        orderEsDTO.setPaymentStatus(orderDO.getPaymentStatus());
        orderEsDTO.setPaymentAmount(orderDO.getPaymentAmount());
        orderEsDTO.setSource(orderDO.getSource());
        orderEsDTO.setPaymentType(orderDO.getPaymentType());
        orderEsDTO.setSplit(orderDO.getSplit());
        orderEsDTO.setOrderTime(orderDO.getGmtCreated());
        if (SplitEnum.IS.getCode().equals(orderDO.getSplit())) {
            List<SubOrderDTO> subOrderList = subOrderDOList.stream().map(subOrderDO -> {
                SubOrderDTO subOrderDTO = new SubOrderDTO();
                subOrderDTO.setSubOrderId(subOrderDO.getId());
                OrderItemDO orderItemDO = orderItemMap.get(subOrderDO.getOrderItemId());
                subOrderDTO.setSkuId(orderItemDO.getSkuId());
                subOrderDTO.setSkuName(orderItemDO.getSkuName());
                subOrderDTO.setSkuMainPic(orderItemDO.getSkuPic());
                return subOrderDTO;
            }).collect(Collectors.toList());
            orderEsDTO.setSubOrderList(subOrderList);
        } else {
            List<SkuDTO> skuList = Lists.newArrayList();
            orderItemMap.forEach((k, v) -> {
                SkuDTO skuDTO = new SkuDTO();
                skuDTO.setSkuId(v.getId());
                skuDTO.setSkuName(v.getSkuName());
                skuDTO.setSkuMainPic(v.getSkuPic());
                skuList.add(skuDTO);
            });
            orderEsDTO.setSkuList(skuList);
        }
        return orderEsDTO;
    }
}

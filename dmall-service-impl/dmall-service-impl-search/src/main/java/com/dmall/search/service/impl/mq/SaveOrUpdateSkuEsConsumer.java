package com.dmall.search.service.impl.mq;

import com.dmall.common.constants.MqConstants;
import com.dmall.search.service.impl.handler.ImportSkuToEsHandler;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改es里sku的信息
 * @author: created by hang.yu on 2020/3/25 23:31
 */
@Component
@RocketMQMessageListener(topic = MqConstants.SYNC_ES_SKU, consumerGroup = "searchConsumer")
public class SaveOrUpdateSkuEsConsumer implements RocketMQListener<Long> {

    @Autowired
    private ImportSkuToEsHandler importSkuToEsHandler;

    @Override
    public void onMessage(Long skuId) {
        importSkuToEsHandler.operateEsSku(skuId);
    }
}

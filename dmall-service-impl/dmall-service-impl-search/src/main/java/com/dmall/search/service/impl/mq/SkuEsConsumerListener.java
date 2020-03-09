package com.dmall.search.service.impl.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SkuEsConsumerListener {

    @JmsListener(destination = MqConstants.SKU_TOPIC, containerFactory = "jmsTopicListenerContainerFactory")
    public void ListenTopic(String msg) {
        System.out.println("接收到topic消息：" + msg);
    }

}
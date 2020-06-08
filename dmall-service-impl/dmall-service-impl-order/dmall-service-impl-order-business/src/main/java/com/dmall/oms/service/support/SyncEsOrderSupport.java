package com.dmall.oms.service.support;

import com.dmall.common.constants.MqConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SyncEsOrderSupport
 * @author: created by hang.yu on 2020/4/9 21:31
 */
@Slf4j
@Component
public class SyncEsOrderSupport {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步订单到es
     */
    public void sendOrderEsMq(Long orderId) {
        rocketMQTemplate.sendAndReceive(MqConstants.SYNC_ES_ORDER, orderId,
            new RocketMQLocalRequestCallback() {
                @Override
                public void onSuccess(Object message) {
                    log.info("sendOrderEsMq success{}", message);
                }

                @SneakyThrows
                @Override
                public void onException(Throwable e) {
                    log.error("sendOrderEsMq error", e);
                }
            });
    }
}

package com.dmall.pms.service.support;

import com.dmall.common.constants.MqConstants;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 同步sku数据到es
 * @author: created by hang.yu on 2020/4/25 16:10
 */
@Slf4j
@Component
public class SkuImportEsSupport {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步sku到es的mq
     */
    public void sendSyncSkuMq(Long skuId) {
        rocketMQTemplate.sendAndReceive(MqConstants.SYNC_ES_SKU, skuId,
            new RocketMQLocalRequestCallback() {
                @Override
                public void onSuccess(Object message) {
                    log.info("send mq success{}", message);
                }

                @SneakyThrows
                @Override
                public void onException(Throwable e) {
                    log.error("send mq error", e);
                    throw new BusinessException(BasicStatusEnum.FAIL);
                }
            }, 3000);
    }
}

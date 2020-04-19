package com.dmall.common.constants;

/**
 * @description: MQ topic 常量
 * @author: created by hang.yu on 2020/4/4 13:41
 */
public interface MqConstants {

    /**
     * 延迟取消订单成功的topic
     */
    String DELAY_CANCEL_ORDER_TOPIC = "delay_cancel_order_topic";

    /**
     * 支付成功的topic
     */
    String PAY_SUCCESS_TOPIC = "pay_success_topic";

    /**
     * 同步sku到es的topic
     */
    String SYNC_ES_SKU = "sync_es_sku_topic";

    /**
     * 同步sku到es的topic
     */
    String SYNC_ES_ORDER = "sync_es_order_topic";
}

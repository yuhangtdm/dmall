package com.dmall.oms.service.impl.order;

/**
 * @description: 下单模块常量
 * @author: created by hang.yu on 2020/4/2 22:43
 */
public interface OrderConstants {

    /**
     * 校验订单提交成功后不能重复提交
     */
    String ORDER_KEY = "memberId_{}";

    /**
     * 校验订单不能短时间重复提交
     */
    String CHECK_ORDER_KEY = "check_memberId_{}";

    /**
     * 订单的消费者组
     */
    String CONSUMER_GROUP = "orderConsumer";
}

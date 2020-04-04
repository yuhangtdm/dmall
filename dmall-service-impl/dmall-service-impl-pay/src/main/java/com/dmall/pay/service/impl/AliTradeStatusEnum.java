package com.dmall.pay.service.impl;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: AliTradeStatusEnum
 * @author: created by hang.yu on 2020/4/4 9:29
 */
@Getter
@AllArgsConstructor
public enum AliTradeStatusEnum implements KeyValueEnum<String> {

    /**
     * 交易完成
     */
    TRADE_FINISHED("TRADE_FINISHED", "交易完成", false),

    /**
     * 支付成功
     */
    TRADE_SUCCESS("TRADE_SUCCESS", "支付成功", true),

    /**
     * 交易创建
     */
    WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建", false),

    /**
     * 交易关闭
     */
    TRADE_CLOSED("TRADE_CLOSED", "交易关闭", false),
    ;

    /**
     * 交易码
     */
    private String code;

    /**
     * 交易描述
     */
    private String desc;

    /**
     * 是否触发通知
     */
    private boolean notify;
}

package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 子订单状态枚举
 * @author: created by hang.yu on 2020/3/28 16:48
 */
@Getter
@AllArgsConstructor
public enum SubOrderStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 待发货
     */
    WAIT_SHIP(2, "待发货"),

    /**
     * 待收货
     */
    WAIT_RECEIVE(3, "待收货"),

    /**
     * 已完成
     */
    COMPLETED(4, "已完成"),

    /**
     * 交易关闭
     */
    CLOSED(6, "交易关闭");
    private final Integer code;

    private final String desc;
}

package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 收货方式枚举
 * @author: created by hang.yu on 2020/4/18 15:03
 */
@Getter
@AllArgsConstructor
public enum DeliverTypeEnum implements CodeDescEnum<Integer> {
    /**
     * 会员手动确认收货
     */
    HANDLE(1, "会员手动确认收货"),

    /**
     * 系统自动确认收货
     */
    AUTO(2, "系统自动确认收货"),
    ;

    /**
     * code
     */
    private final Integer code;

    /**
     * desc
     */
    private final String desc;
}
package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: OrderDeliverStatusEnum
 * @author: created by hang.yu on 2020/4/6 10:19
 */
@Getter
@AllArgsConstructor
public enum OrderReceiveStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 待收货
     */
    WAIT(1, "待收货"),

    /**
     * 部分收货
     */
    PART(2, "部分收货"),

    /**
     * 全部收货
     */
    ALL(3, "全部收货");
    private final Integer code;

    private final String desc;
}

package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 订单来源枚举
 * @author: created by hang.yu on 2020/3/28 16:52
 */
@Getter
@AllArgsConstructor
public enum OrderSourceEnum implements CodeDescEnum<Integer> {

    /**
     * PC
     */
    PC(1, "PC"),

    /**
     * APP
     */
    APP(2, "APP"),

    /**
     * 小程序
     */
    SMALL_PROGRAM(3, "小程序"),


    ;
    private Integer code;

    private String desc;
}


package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 单位枚举
 * @author: created by hang.yu on 2020/1/2 22:13
 */
@Getter
@AllArgsConstructor
public enum UnitEnum implements CodeDescEnum<String> {
    /**
     * kg
     */
    KG("kg", "千克"),

    /**
     * g
     */
    G("g", "克"),
    ;
    private final String code;

    private final String desc;
}

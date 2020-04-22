package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 单位枚举
 * @author: created by hang.yu on 2020/1/2 22:13
 */
@Getter
@AllArgsConstructor
public enum UnitEnum implements CodeEnum<String> {
    /**
     * kg
     */
    KG("kg"),

    /**
     * g
     */
    G("g"),
    ;
    private final String code;
}

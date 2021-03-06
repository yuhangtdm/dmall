package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性录入方式
 * @author: created by hang.yu on 2019/12/5 23:08
 */
@Getter
@AllArgsConstructor
public enum InputTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 手工录入
     */
    HANDLE(1, "手工录入"),

    /**
     * 从列表获取
     */
    LIST(2, "从列表获取");

    private final Integer code;

    private final String desc;
}

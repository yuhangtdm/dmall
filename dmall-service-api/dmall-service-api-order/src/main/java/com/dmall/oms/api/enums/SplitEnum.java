package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SplitEnum
 * @author: created by hang.yu on 2020/4/4 15:56
 */
@Getter
@AllArgsConstructor
public enum SplitEnum implements CodeDescEnum<Integer> {
    /**
     * 未拆分
     */
    NOT(1, "未拆分"),

    /**
     * 无需拆分
     */
    NOT_NEED(2, "无需拆分"),

    /**
     * 已拆分
     */
    IS(3, "已拆分"),

    ;

    private final Integer code;

    private final String desc;
}

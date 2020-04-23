package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 取消类型枚举
 * @author: created by hang.yu on 2020/3/28 16:57
 */
@Getter
@AllArgsConstructor
public enum CancelTypeEnum implements CodeDescEnum<Integer> {
    /**
     * 手动取消
     */
    HANDLE(1, "手动取消"),

    /**
     * 自动取消
     */
    AUTO(2, "自动取消"),
    ;


    private final Integer code;

    private final String desc;
}


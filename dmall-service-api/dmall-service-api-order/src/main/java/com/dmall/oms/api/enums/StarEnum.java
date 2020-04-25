package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评分枚举
 * @author: created by hang.yu on 2020/4/25 10:05
 */
@Getter
@AllArgsConstructor
public enum StarEnum implements CodeEnum<Integer> {

    /**
     * 1
     */
    ONE(1),

    /**
     * 2
     */
    TWO(2),

    /**
     * 3
     */
    THREE(3),

    /**
     * 4
     */
    FOUR(4),

    /**
     * 5
     */
    FIVE(5),
    ;
    private final Integer code;
}

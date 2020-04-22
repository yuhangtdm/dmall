package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: LevelEnum
 * @author: created by hang.yu on 2019/11/24 14:58
 */
@Getter
@AllArgsConstructor
public enum LevelEnum implements CodeDescEnum<Integer> {

    /**
     * 1级
     */
    ONE(1, "1级"),

    /**
     * 2级
     */
    TWO(2, "2级"),

    /**
     * 3级
     */
    THREE(3, "3级"),
    ;

    private final Integer code;

    private final String desc;
}

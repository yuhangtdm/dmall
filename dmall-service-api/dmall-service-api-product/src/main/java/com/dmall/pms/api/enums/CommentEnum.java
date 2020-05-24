package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: CommentLevelEnum
 * @author: created by hang.yu on 2020/4/12 15:35
 */
@Getter
@AllArgsConstructor
public enum CommentEnum implements CodeDescEnum<Integer> {
    /**
     * 1
     */
    GOOD(1, "好评"),

    /**
     * 2
     */
    MIDDLE(2, "中评"),

    /**
     * 3
     */
    BAD(3, "差评"),

    ;

    /**
     * 评分
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;


}

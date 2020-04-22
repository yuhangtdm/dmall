package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescDataEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: CommentLevelEnum
 * @author: created by hang.yu on 2020/4/12 15:35
 */
@Getter
@AllArgsConstructor
public enum CommentLevelEnum implements CodeDescDataEnum<Integer, Integer> {
    /**
     * 1
     */
    ONE(1, "差评", 3),

    /**
     * 2
     */
    TOW(2, "差评", 3),

    /**
     * 3
     */
    THREE(3, "中评", 2),

    /**
     * 4
     */
    FORE(4, "好评", 1),

    /**
     * 5
     */
    FIVE(5, "好评", 1);

    /**
     * 评分
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 等级
     */
    private final Integer data;
}

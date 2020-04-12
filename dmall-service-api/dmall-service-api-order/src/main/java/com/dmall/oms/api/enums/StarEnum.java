package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评分枚举
 * @author: created by hang.yu on 2020/4/12 15:51
 */
@Getter
@AllArgsConstructor
public enum StarEnum implements CodeEnum<Integer> {
    ONE(1),
    TWO(2),
    THREE(3),
    FORE(4),
    FIVE(5);
    private Integer code;
}

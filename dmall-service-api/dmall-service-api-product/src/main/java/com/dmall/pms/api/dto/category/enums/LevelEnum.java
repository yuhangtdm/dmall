package com.dmall.pms.api.dto.category.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: LevelEnum
 * @author: created by hang.yu on 2019/11/24 14:58
 */
@Getter
@AllArgsConstructor
public enum LevelEnum implements KeyValueEnum<Integer> {
    ONE(1, "1级"),
    TWO(2, "2级"),
    THREE(3, "3级"),
    ;

    private Integer code;

    private String desc;
}

package com.dmall.pms.api.dto.category.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: CanScreenEnum
 * @author: created by hang.yu on 2019/12/25 23:15
 */
@Getter
@AllArgsConstructor
public enum CanScreenEnum implements KeyValueEnum<Integer> {

    NOT(1, "不可筛选" ),
    SINGLE(2, "单选" ),
    MULTI(3, "多选" ),
    ;

    private Integer code;

    private String desc;
}

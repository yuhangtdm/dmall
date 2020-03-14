package com.dmall.oms.api.dto.cart.select;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SelectTypeEnum
 * @author: created by hang.yu on 2020/3/14 17:53
 */
@Getter
@AllArgsConstructor
public enum SelectTypeEnum implements KeyValueEnum<Integer> {

    CHECK(1,"勾选"),
    CANCEL(2,"取消勾选")

    ;
    private Integer code;

    private String desc;
}

package com.dmall.pms.api.dto.attribute.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性录入方式
 * @author: created by hang.yu on 2019/12/5 23:08
 */
@Getter
@AllArgsConstructor
public enum InputTypeEnum implements KeyValueEnum<Integer> {

    HANDLE(1, "手工录入" ),
    LIST(2, "从列表获取" );

    private Integer code;

    private String desc;
}

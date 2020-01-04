package com.dmall.pms.api.dto.attribute.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性类型
 * @author: created by hang.yu on 2019/12/5 23:08
 */
@Getter
@AllArgsConstructor
public enum TypeEnum implements KeyValueEnum<Integer> {

    NORMAL(1, "普通属性" ),
    PUBLIC(2, "公共属性" );

    private Integer code;

    private String desc;
}

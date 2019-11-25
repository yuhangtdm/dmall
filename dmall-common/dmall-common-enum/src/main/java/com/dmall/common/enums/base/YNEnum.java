package com.dmall.common.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: YNEnum
 * @author: created by hang.yu on 2019/11/24 15:01
 */
@Getter
@AllArgsConstructor
public enum YNEnum implements KeyValueEnum<String> {

    Y("Y","是"),
    N("N","否")
    ;
    private String code;

    private String desc;

}

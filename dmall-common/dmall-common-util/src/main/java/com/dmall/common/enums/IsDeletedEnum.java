package com.dmall.common.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: IsDeletedEnum
 * @author: created by hang.yu on 2019/11/23 10:40
 */
@Getter
@AllArgsConstructor
public enum IsDeletedEnum implements KeyValueEnum {

    Y("Y", "不可用"),
    N("N", "可用");
    private String code;

    private String desc;

}

package com.dmall.pms.api.dto.attribute.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: HandAddStatusEnum
 * @author: created by hang.yu on 2019/12/7 23:01
 */
@Getter
@AllArgsConstructor
public enum  HandAddStatusEnum implements KeyValueEnum<String> {

    Y("Y","支持"),
    N("N","不支持"),
    ;

    private String code;

    private String desc;
}

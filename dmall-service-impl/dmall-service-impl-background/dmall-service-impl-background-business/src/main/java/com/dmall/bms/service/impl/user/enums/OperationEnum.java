package com.dmall.bms.service.impl.user.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2020/2/20 22:43
 */
@Getter
@AllArgsConstructor
public enum OperationEnum implements KeyValueEnum<String> {
    ADD("+","加权限"),
    SUB("-","减权限")
    ;

    private String code;

    private String desc;
}

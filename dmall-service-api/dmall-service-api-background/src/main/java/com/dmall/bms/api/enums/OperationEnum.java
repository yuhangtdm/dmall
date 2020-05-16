package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: OperationEnum
 * @author: created by hang.yu on 2020/2/20 22:43
 */
@Getter
@AllArgsConstructor
public enum OperationEnum implements CodeDescEnum<String> {
    /**
     * 加权限
     */
    ADD("+","加权限"),

    /**
     * 减权限
     */
    SUB("-","减权限")
    ;

    private final String code;

    private final String desc;
}

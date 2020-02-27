package com.dmall.mms.service.impl.member.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: MaritalStatusEnum
 * @author: created by hang.yu on 2020/2/23 22:10
 */
@Getter
@AllArgsConstructor
public enum MaritalStatusEnum implements KeyValueEnum<Integer> {

    MARRIED(1,"已婚"),
    UNMARRIED(2,"未婚"),
    SECRECY(3,"保密")

  ;
    private Integer code;

    private String desc;
}

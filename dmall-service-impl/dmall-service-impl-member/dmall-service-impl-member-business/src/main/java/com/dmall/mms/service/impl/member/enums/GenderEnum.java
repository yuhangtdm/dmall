package com.dmall.mms.service.impl.member.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: GenderEnum
 * @author: created by hang.yu on 2020/2/23 22:10
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements KeyValueEnum<Integer> {

    MALE(1,"男"),
    FEMALE(2,"女"),
    SECRECY(3,"保密")

    ;
    private Integer code;

    private String desc;
}

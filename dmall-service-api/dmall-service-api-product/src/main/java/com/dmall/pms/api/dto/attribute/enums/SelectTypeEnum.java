package com.dmall.pms.api.dto.attribute.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性选择类型
 * @author: created by hang.yu on 2019/12/5 23:10
 */
@Getter
@AllArgsConstructor
public enum  SelectTypeEnum implements KeyValueEnum<Integer> {

    HANDLE(1,"单选"),

    LIST(2,"多选")
    ;


    private Integer code;

    private String desc;
}


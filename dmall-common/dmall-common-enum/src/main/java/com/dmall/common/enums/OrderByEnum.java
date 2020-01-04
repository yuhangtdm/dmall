package com.dmall.common.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/4 22:00
 */
@Getter
@AllArgsConstructor
public enum OrderByEnum implements KeyValueEnum<String> {
    ASC("ASC" , "升序" ),
    DESC("DESC" , "降序" ),
    ;
    private String code;

    private String desc;
}

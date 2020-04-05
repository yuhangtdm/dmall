package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发货状态枚举
 * @author: created by hang.yu on 2020/4/5 10:45
 */
@Getter
@AllArgsConstructor
public enum DeliverStatusEnum implements KeyValueEnum<String> {

    Y("Y", "已发货"),

    N("N", "未发货"),

    ;
    private String code;

    private String desc;
}

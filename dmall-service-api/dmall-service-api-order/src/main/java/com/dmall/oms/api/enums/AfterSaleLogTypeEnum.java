package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 售后日志类型枚举
 * @author: created by hang.yu on 2020/4/16 22:00
 */
@Getter
@AllArgsConstructor
public enum AfterSaleLogTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 会员
     */
    MEMBER(1, "会员"),

    /**
     * 系统
     */
    SYSTEM(2, "系统"),
    ;

    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;
}

package com.dmall.bms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 菜单表 错误枚举
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Getter
@AllArgsConstructor
public enum MenuErrorEnum implements ErrorCodeEnum {

    PARENT_NOT_EXIST("1000", "上级不存在"),
    PARENT_NOT_CATALOG("1001", "上级不是目录"),
    URL_BLANK("1002","地址不能为空"),
    PARENT_NOT_NULL("1003","父级id不能为空"),
    MENU_NOT_EXIST("2000", "该菜单不存在"),

    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}

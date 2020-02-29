package com.dmall.pms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum AttributeErrorEnum implements ErrorCodeEnum {

    CATEGORY_NOT_EXIST("1000", "商品分类不存在"),
    CATEGORY_NOT_INVALID("1001", "商品分类级别不合法"),
    ATTRIBUTE_DATA_INVALID("1002", "该属性参数无效"),
    INPUT_LIST_INVALID("1003", "可选值列表不能重复"),
    CONTAINS_PRODUCT("1004", "该属性下有商品,不可删除"),
    ATTRIBUTE_NOT_EXIST("1005", "该属性不存在"),
    ATTRIBUTE_TYPE_INVALID("1006", "该公共属性不可改为普通属性"),

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

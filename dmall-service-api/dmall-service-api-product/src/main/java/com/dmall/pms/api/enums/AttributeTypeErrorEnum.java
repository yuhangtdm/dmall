package com.dmall.pms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性分类错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum AttributeTypeErrorEnum implements ErrorCodeEnum {

    ATTRIBUTE_TYPE_NOT_EXIST("1001", "该属性分类不存在"),
    ATTRIBUTE_TYPE_HAS_PRODUCT("1002", "该属性分类下有商品,不可删除"),
    CATEGORY_NOT_EXIST("1003", "商品分类不存在"),

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

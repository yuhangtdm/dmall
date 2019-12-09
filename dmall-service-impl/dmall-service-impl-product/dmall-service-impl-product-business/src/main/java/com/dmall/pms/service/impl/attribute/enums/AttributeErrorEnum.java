package com.dmall.pms.service.impl.attribute.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum  AttributeErrorEnum implements ErrorCodeEnum {

    SAVE_ATTRIBUTE_ERROR("attribute-001","新增属性失败"),
    UPDATE_ATTRIBUTE_ERROR("attribute _002","修改属性失败"),
    DELETE_ATTRIBUTE_ERROR("attribute_003","该属性下有商品"),
    ATTRIBUTE_NOT_EXIST("attribute_004","该属性不存在"),
    ATTRIBUTE_NAME_UNIQUE("attribute_005","该属性分类下的属性名称已存在"),
    ATTRIBUTE_TYPE_EXIST("attribute_005","属性分类不存在"),

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

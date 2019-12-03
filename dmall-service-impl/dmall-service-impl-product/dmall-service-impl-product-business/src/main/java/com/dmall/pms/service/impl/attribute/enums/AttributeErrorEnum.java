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
    DELETE_ATTRIBUTE_ERROR("attribute_003","删除属性失败"),
    ATTRIBUTE_NOT_EXIST("attribute_004","该属性不存在"),

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

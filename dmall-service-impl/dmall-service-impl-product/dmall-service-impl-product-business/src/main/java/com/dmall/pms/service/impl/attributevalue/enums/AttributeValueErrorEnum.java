package com.dmall.pms.service.impl.attributevalue.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性值错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum  AttributeValueErrorEnum implements ErrorCodeEnum {

    SAVE_ATTRIBUTEVALUE_ERROR("attributeValue-001","新增属性值失败"),
    UPDATE_ATTRIBUTEVALUE_ERROR("attributeValue _002","修改属性值失败"),
    DELETE_ATTRIBUTEVALUE_ERROR("attributeValue_003","删除属性值失败"),
    ATTRIBUTEVALUE_NOT_EXIST("attributeValue_004","该属性值不存在"),

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

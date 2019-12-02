package com.dmall.pms.service.impl.attributetype.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性分类错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum  AttributeTypeErrorEnum implements ErrorCodeEnum {

    SAVE_ATTRIBUTETYPE_ERROR("attributeType-001","新增属性分类失败"),
    UPDATE_ATTRIBUTETYPE_ERROR("attributeType _002","修改属性分类失败"),
    DELETE_ATTRIBUTETYPE_ERROR("attributeType_003","删除属性分类失败"),
    ATTRIBUTETYPE_NOT_EXIST("attributeType_004","该属性分类不存在"),

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

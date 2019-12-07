package com.dmall.pms.service.impl.productattribute.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性值错误枚举
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Getter
@AllArgsConstructor
public enum  ProductAttributeErrorEnum implements ErrorCodeEnum {

    SAVE_PRODUCTATTRIBUTE_ERROR("productAttribute-001","新增属性值失败"),
    UPDATE_PRODUCTATTRIBUTE_ERROR("productAttribute _002","修改属性值失败"),
    DELETE_PRODUCTATTRIBUTE_ERROR("productAttribute_003","删除属性值失败"),
    PRODUCTATTRIBUTE_NOT_EXIST("productAttribute_004","该属性值不存在"),

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

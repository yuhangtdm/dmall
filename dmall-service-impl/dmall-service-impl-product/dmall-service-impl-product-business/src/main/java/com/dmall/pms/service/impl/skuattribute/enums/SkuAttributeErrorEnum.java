package com.dmall.pms.service.impl.skuattribute.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: sku属性错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Getter
@AllArgsConstructor
public enum  SkuAttributeErrorEnum implements ErrorCodeEnum {

    SAVE_SKUATTRIBUTE_ERROR("skuAttribute-001","新增sku属性失败"),
    UPDATE_SKUATTRIBUTE_ERROR("skuAttribute _002","修改sku属性失败"),
    DELETE_SKUATTRIBUTE_ERROR("skuAttribute_003","删除sku属性失败"),
    SKUATTRIBUTE_NOT_EXIST("skuAttribute_004","该sku属性不存在"),

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

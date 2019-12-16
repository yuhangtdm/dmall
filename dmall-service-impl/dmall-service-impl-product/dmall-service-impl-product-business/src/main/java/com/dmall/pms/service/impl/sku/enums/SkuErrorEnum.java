package com.dmall.pms.service.impl.sku.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: sku错误枚举
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Getter
@AllArgsConstructor
public enum SkuErrorEnum implements ErrorCodeEnum {

    SAVE_SKU_ERROR("sku-001","新增sku失败"),
    UPDATE_SKU_ERROR("sku _002","修改sku失败"),
    DELETE_SKU_ERROR("sku_003","删除sku失败"),
    SKU_NOT_EXIST("sku_004","该sku不存在"),

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

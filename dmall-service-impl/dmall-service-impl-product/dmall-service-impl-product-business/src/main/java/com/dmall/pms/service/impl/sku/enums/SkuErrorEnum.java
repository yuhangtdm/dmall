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

    PRODUCT_NOT_EXISTS("sku-001","该商品不存在"),
    SKU_NAME_EXISTS("sku-001","该sku名称已存在"),
    UPDATE_SKU_ERROR("sku-002","修改sku失败"),
    DELETE_SKU_ERROR("sku-003","删除sku失败"),
    SKU_NOT_EXIST("sku-004","该sku不存在"),
    MEDIA_NOT_EXIST("sku-005","媒体列表为空"),

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

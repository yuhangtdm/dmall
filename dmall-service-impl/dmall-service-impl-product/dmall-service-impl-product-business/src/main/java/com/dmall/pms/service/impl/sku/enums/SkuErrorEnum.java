package com.dmall.pms.service.impl.sku.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: sku错误枚举
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Getter
@AllArgsConstructor
public enum SkuErrorEnum implements ErrorCodeEnum {

    PRODUCT_NOT_EXISTS("sku_001", "该商品不存在"),
    SKU_NAME_EXISTS("sku_002", "该sku名称已存在"),
    SKU_NOT_EXIST("sku_003", "该sku不存在"),
    MEDIA_NOT_EXIST("sku_004", "媒体列表为空"),

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

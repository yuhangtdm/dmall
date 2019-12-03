package com.dmall.pms.service.impl.product.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Getter
@AllArgsConstructor
public enum  ProductErrorEnum implements ErrorCodeEnum {

    SAVE_PRODUCT_ERROR("product-001","新增商品失败"),
    UPDATE_PRODUCT_ERROR("product _002","修改商品失败"),
    DELETE_PRODUCT_ERROR("product_003","删除商品失败"),
    PRODUCT_NOT_EXIST("product_004","该商品不存在"),

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

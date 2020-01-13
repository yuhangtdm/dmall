package com.dmall.pms.service.impl.product.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Getter
@AllArgsConstructor
public enum ProductErrorEnum implements ErrorCodeEnum {

    CATEGORY_NOT_EXISTS("1001", "商品分类不存在"),
    CATEGORY_LEVEL_ERROR("1002", "商品分类级别不合法"),
    BRAND_NOT_EXISTS("1003", "品牌不存在"),
    CATEGORY_NOT_REPEATED("1004", "商品分类重复"),

    ATTRIBUTE_NOT_EXISTS("2001", "属性不存在"),
    ATTRIBUTE_TYPE_NOT_EXISTS("2002", "属性分类不存在"),

    PRODUCT_NAME_EXISTS("3001", "商品名称已存在"),
    DELETE_PRODUCT_ERROR("3002", "删除商品失败"),
    PRODUCT_NOT_EXIST("3003", "该商品不存在"),

    UPLOAD_PRODUCT_ERROR("4001", "上传商品主图失败"),
    UPLOAD_PRODUCT_ATTRIBUTE_ERROR("4002", "上传商品规格配图失败"),
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

package com.dmall.pms.business.service.brand.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: BrandErrorEnum
 * @author: created by hang.yu on 2019/11/21 22:17
 */
@Getter
@AllArgsConstructor
public enum  BrandErrorEnum implements ErrorCodeEnum {

    SAVE_BRAND_ERROR("BRAND-001","保存品牌失败"),
    BRAND_NAME_UNIQUE("BRAND-002","品牌名称已存在"),
    UPDATE_BRAND_ERROR("BRAND-003","修改品牌失败"),
    DELETE_BRAND_ERROR("BRAND-004","删除品牌失败"),
    BRAND_NOT_EXIST("BRAND-005","该品牌不存在"),
    CONTAINS_PRODUCT_ERROR("BRAND-006","该品牌下有商品,不允许删除"),
    CONTAINS_ATTRIBUTE_TYPE_ERROR("BRAND-007","该品牌下有商品分类,不允许删除"),
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

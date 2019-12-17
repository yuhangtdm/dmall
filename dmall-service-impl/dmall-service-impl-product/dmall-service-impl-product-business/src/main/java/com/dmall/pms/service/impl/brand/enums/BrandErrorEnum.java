package com.dmall.pms.service.impl.brand.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 品牌错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum BrandErrorEnum implements ErrorCodeEnum {

    SAVE_BRAND_ERROR("brand-001", "新增品牌失败"),
    UPDATE_BRAND_ERROR("brand-002", "修改品牌失败"),
    DELETE_BRAND_ERROR("brand-003", "删除品牌失败"),
    BRAND_NOT_EXIST("brand-004", "该品牌不存在"),
    CONTAINS_PRODUCT_ERROR("brand-005", "该品牌下有商品,不允许删除"),
    CONTAINS_ATTRIBUTE_TYPE_ERROR("brand-006", "该品牌下有商品分类,不允许删除"),
    BRAND_NAME_UNIQUE("brand-007", "品牌名称已存在"),
    UPLOAD_LOGO_ERROR("brand-008","上传品牌logo失败"),
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

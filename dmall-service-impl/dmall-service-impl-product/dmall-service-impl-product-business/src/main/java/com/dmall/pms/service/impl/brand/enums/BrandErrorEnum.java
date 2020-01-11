package com.dmall.pms.service.impl.brand.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 品牌错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Getter
@AllArgsConstructor
public enum BrandErrorEnum implements ErrorCodeEnum {

    BRAND_NOT_EXIST("brand-001", "该品牌不存在"),
    CONTAINS_PRODUCT_ERROR("brand_002", "该品牌下有商品,不允许删除"),
    BRAND_NAME_UNIQUE("brand_003", "品牌名称已存在"),
    UPLOAD_LOGO_ERROR("brand_004", "上传品牌logo失败"),
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

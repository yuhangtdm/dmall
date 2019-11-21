package com.dmall.pms.business.service.brand.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by yuhang on 2019/11/21 22:17
 */
@Getter
@AllArgsConstructor
public enum  BrandErrorEnum implements ErrorCodeEnum {

    SAVE_BRAND_ERROR("BRAND-001","保存品牌失败"),
    BRAND_NAME_UNIQUE("BRAND-002","品牌名称已存在"),

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

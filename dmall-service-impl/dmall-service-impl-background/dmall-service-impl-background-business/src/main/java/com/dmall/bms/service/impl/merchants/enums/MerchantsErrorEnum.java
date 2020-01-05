package com.dmall.bms.service.impl.merchants.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商家店铺表 1期只有一家店铺错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Getter
@AllArgsConstructor
public enum MerchantsErrorEnum implements ErrorCodeEnum {

    SAVE_MERCHANTS_ERROR("merchants-001", "新增商家店铺表 1期只有一家店铺失败"),
    UPDATE_MERCHANTS_ERROR("merchants _002", "修改商家店铺表 1期只有一家店铺失败"),
    DELETE_MERCHANTS_ERROR("merchants_003", "删除商家店铺表 1期只有一家店铺失败"),
    MERCHANTS_NOT_EXIST("merchants_004", "该商家店铺表 1期只有一家店铺不存在"),

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

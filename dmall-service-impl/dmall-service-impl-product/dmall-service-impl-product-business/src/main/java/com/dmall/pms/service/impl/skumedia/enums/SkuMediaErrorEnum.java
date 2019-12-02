package com.dmall.pms.service.impl.skumedia.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: sku媒体对象错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Getter
@AllArgsConstructor
public enum  SkuMediaErrorEnum implements ErrorCodeEnum {

    SAVE_SKUMEDIA_ERROR("skuMedia-001","新增sku媒体对象失败"),
    UPDATE_SKUMEDIA_ERROR("skuMedia _002","修改sku媒体对象失败"),
    DELETE_SKUMEDIA_ERROR("skuMedia_003","删除sku媒体对象失败"),
    SKUMEDIA_NOT_EXIST("skuMedia_004","该sku媒体对象不存在"),

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

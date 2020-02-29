package com.dmall.mms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收藏sku错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum MemberSkuErrorEnum implements ErrorCodeEnum {

    MEMBER_COLLECTION_SKU_NOT_EXIST("1000","该会员收藏的sku不存在"),

    MEMBER_COLLECTION_SKU_ERROR("2000","您不可取消他人收藏的sku"),

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

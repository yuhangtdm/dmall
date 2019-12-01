package com.dmall.mms.service.impl.membercollectionsku.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收藏sku错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberCollectionSkuErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERCOLLECTIONSKU_ERROR("memberCollectionSku-001","新增会员收藏sku失败"),
    UPDATE_MEMBERCOLLECTIONSKU_ERROR("memberCollectionSku _002","修改会员收藏sku失败"),
    DELETE_MEMBERCOLLECTIONSKU_ERROR("memberCollectionSku_003","删除会员收藏sku失败"),
    MEMBERCOLLECTIONSKU_NOT_EXIST("memberCollectionSku_004","该会员收藏sku不存在"),

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

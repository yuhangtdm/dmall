package com.dmall.mms.service.impl.comment.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品评价错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum CommentErrorEnum implements ErrorCodeEnum {

    COMMENT_NOT_EXIST("comment_100","该商品评价不存在"),

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

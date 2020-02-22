package com.dmall.mms.service.impl.commentreplypraise.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 回复点赞错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum CommentReplyPraiseErrorEnum implements ErrorCodeEnum {

    COMMENTREPLYPRAISE_NOT_EXIST("commentReplyPraise_100","该回复点赞不存在"),

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

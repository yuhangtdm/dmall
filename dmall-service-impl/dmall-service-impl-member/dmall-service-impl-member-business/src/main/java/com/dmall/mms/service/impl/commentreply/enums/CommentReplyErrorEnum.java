package com.dmall.mms.service.impl.commentreply.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评价回复错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum CommentReplyErrorEnum implements ErrorCodeEnum {

    COMMENTREPLY_NOT_EXIST("commentReply_100","该评价回复不存在"),

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

package com.dmall.mms.service.impl.commentpraise.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评论点赞错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Getter
@AllArgsConstructor
public enum CommentPraiseErrorEnum implements ErrorCodeEnum {

    COMMENTPRAISE_NOT_EXIST("commentPraise_100","该评论点赞不存在"),

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

package com.dmall.pms.service.impl.commentreplypraise.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 回复点赞错误枚举
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Getter
@AllArgsConstructor
public enum CommentReplyPraiseErrorEnum implements ErrorCodeEnum {

    SAVE_COMMENTREPLYPRAISE_ERROR("commentReplyPraise-001","新增回复点赞失败"),
    UPDATE_COMMENTREPLYPRAISE_ERROR("commentReplyPraise _002","修改回复点赞失败"),
    DELETE_COMMENTREPLYPRAISE_ERROR("commentReplyPraise_003","删除回复点赞失败"),
    COMMENTREPLYPRAISE_NOT_EXIST("commentReplyPraise_004","该回复点赞不存在"),

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

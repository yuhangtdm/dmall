package com.dmall.pms.service.impl.commentpraise.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评论点赞数错误枚举
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Getter
@AllArgsConstructor
public enum CommentPraiseErrorEnum implements ErrorCodeEnum {

    SAVE_COMMENTPRAISE_ERROR("commentPraise-001","新增评论点赞数失败"),
    UPDATE_COMMENTPRAISE_ERROR("commentPraise _002","修改评论点赞数失败"),
    DELETE_COMMENTPRAISE_ERROR("commentPraise_003","删除评论点赞数失败"),
    COMMENTPRAISE_NOT_EXIST("commentPraise_004","该评论点赞数不存在"),

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

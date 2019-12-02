package com.dmall.pms.service.impl.commentreply.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 评价回复错误枚举
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Getter
@AllArgsConstructor
public enum  CommentReplyErrorEnum implements ErrorCodeEnum {

    SAVE_COMMENTREPLY_ERROR("commentReply-001","新增评价回复失败"),
    UPDATE_COMMENTREPLY_ERROR("commentReply _002","修改评价回复失败"),
    DELETE_COMMENTREPLY_ERROR("commentReply_003","删除评价回复失败"),
    COMMENTREPLY_NOT_EXIST("commentReply_004","该评价回复不存在"),

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

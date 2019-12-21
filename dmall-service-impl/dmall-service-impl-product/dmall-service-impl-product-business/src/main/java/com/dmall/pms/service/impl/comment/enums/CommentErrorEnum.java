package com.dmall.pms.service.impl.comment.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商品评价错误枚举
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Getter
@AllArgsConstructor
public enum CommentErrorEnum implements ErrorCodeEnum {

    SAVE_COMMENT_ERROR("comment-001","新增商品评价失败"),
    UPDATE_COMMENT_ERROR("comment _002","修改商品评价失败"),
    DELETE_COMMENT_ERROR("comment_003","删除商品评价失败"),
    COMMENT_NOT_EXIST("comment_004","该商品评价不存在"),

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

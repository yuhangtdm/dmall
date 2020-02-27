package com.dmall.mms.api.dto.commentreplypraise.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 回复点赞公共请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonCommentReplyPraiseRequestDTO", description = "回复点赞公共请求实体")
public class CommonCommentReplyPraiseRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "评论id", position = 3)
    private Long commentId;

    @ApiModelProperty(value = "回复id", position = 4)
    private Long replyId;







}

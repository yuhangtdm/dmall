package com.dmall.pms.api.dto.commentreplypraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 回复点赞列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListCommentReplyPraiseRequestDTO", description="回复点赞列表请求实体")
public class ListCommentReplyPraiseRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "评论id", position = 3)
    private Long commentId;


    @ApiModelProperty(value = "回复id", position = 4)
    private Long replyId;












}

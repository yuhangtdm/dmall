package com.dmall.mms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 评论点赞列表请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListCommentPraiseRequestDTO", description = "评论点赞列表请求实体")
public class ListCommentPraiseRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "评论id", position = 3)
        private Long commentId;







}

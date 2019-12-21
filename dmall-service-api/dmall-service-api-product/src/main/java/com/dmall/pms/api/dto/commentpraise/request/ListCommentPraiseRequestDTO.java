package com.dmall.pms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 评论点赞数列表请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListCommentPraiseRequestDTO", description = "评论点赞数列表请求实体")
public class ListCommentPraiseRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "评论id", position = 3)
    private Long conmentId;












}

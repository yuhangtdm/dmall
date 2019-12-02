package com.dmall.pms.api.dto.commentreplypraise.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 回复点赞公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonCommentReplyPraiseResponseDTO", description="回复点赞公共响应实体")
public class CommonCommentReplyPraiseResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;



    @ApiModelProperty(value = "评论id", position = 3)
    private Long commentId;



    @ApiModelProperty(value = "回复id", position = 4)
    private Long replyId;



    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}

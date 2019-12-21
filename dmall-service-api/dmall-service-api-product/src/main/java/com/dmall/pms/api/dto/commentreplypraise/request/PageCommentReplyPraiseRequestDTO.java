package com.dmall.pms.api.dto.commentreplypraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 回复点赞分页请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCommentReplyPraiseRequestDTO", description =  "回复点赞分页请求实体")
public class PageCommentReplyPraiseRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "评论id", position = 3)
    private Long commentId;

    @ApiModelProperty(value = "回复id", position = 4)
    private Long replyId;






}

package com.dmall.mms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 评论点赞分页请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCommentPraiseRequestDTO", description =  "评论点赞分页请求实体")
public class PageCommentPraiseRequestDTO  extends PageRequestDTO {



        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "评论id", position = 3)
        private Long commentId;







}

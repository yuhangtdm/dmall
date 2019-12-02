package com.dmall.pms.api.dto.commentreplypraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseRequestDTO;

/**
 * @description: 修改回复点赞请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateCommentReplyPraiseRequestDTO", description="修改回复点赞请求实体")
public class UpdateCommentReplyPraiseRequestDTO extends CommonCommentReplyPraiseRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}

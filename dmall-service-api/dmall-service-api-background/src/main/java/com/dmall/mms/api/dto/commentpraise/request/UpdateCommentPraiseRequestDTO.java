package com.dmall.mms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.commentpraise.common.CommonCommentPraiseRequestDTO;

/**
 * @description: 修改评论点赞请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCommentPraiseRequestDTO", description = "修改评论点赞请求实体")
public class UpdateCommentPraiseRequestDTO extends CommonCommentPraiseRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}

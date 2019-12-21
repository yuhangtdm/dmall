package com.dmall.pms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.commentpraise.common.CommonCommentPraiseRequestDTO;

/**
 * @description: 修改评论点赞数请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCommentPraiseRequestDTO", description = "修改评论点赞数请求实体")
public class UpdateCommentPraiseRequestDTO extends CommonCommentPraiseRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}

package com.dmall.pms.api.dto.commentpraise.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.commentpraise.common.CommonCommentPraiseRequestDTO;

/**
 * @description: 新增评论点赞数请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveCommentPraiseRequestDTO", description="新增评论点赞数请求实体")
public class SaveCommentPraiseRequestDTO extends CommonCommentPraiseRequestDTO {

}

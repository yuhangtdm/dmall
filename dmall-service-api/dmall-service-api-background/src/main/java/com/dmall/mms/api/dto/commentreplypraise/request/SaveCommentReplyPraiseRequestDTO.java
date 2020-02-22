package com.dmall.mms.api.dto.commentreplypraise.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseRequestDTO;

/**
 * @description: 新增回复点赞请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveCommentReplyPraiseRequestDTO", description = "新增回复点赞请求实体")
public class SaveCommentReplyPraiseRequestDTO extends CommonCommentReplyPraiseRequestDTO {

}

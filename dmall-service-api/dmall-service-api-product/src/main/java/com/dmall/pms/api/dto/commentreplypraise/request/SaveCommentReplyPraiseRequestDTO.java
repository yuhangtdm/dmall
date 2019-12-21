package com.dmall.pms.api.dto.commentreplypraise.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.commentreplypraise.common.CommonCommentReplyPraiseRequestDTO;

/**
 * @description: 新增回复点赞请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveCommentReplyPraiseRequestDTO", description = "新增回复点赞请求实体")
public class SaveCommentReplyPraiseRequestDTO extends CommonCommentReplyPraiseRequestDTO {

}

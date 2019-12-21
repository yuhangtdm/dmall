package com.dmall.pms.api.dto.commentreply.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.commentreply.common.CommonCommentReplyRequestDTO;

/**
 * @description: 新增评价回复请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveCommentReplyRequestDTO", description = "新增评价回复请求实体")
public class SaveCommentReplyRequestDTO extends CommonCommentReplyRequestDTO {

}

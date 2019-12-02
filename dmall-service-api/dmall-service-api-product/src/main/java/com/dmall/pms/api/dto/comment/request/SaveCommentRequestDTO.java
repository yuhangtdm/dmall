package com.dmall.pms.api.dto.comment.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.comment.common.CommonCommentRequestDTO;

/**
 * @description: 新增商品评价请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveCommentRequestDTO", description="新增商品评价请求实体")
public class SaveCommentRequestDTO extends CommonCommentRequestDTO {

}

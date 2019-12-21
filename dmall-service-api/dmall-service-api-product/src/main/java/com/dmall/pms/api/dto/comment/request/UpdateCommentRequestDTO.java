package com.dmall.pms.api.dto.comment.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.comment.common.CommonCommentRequestDTO;

/**
 * @description: 修改商品评价请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCommentRequestDTO", description = "修改商品评价请求实体")
public class UpdateCommentRequestDTO extends CommonCommentRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}

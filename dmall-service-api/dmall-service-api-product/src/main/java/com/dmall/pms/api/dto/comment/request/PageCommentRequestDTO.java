package com.dmall.pms.api.dto.comment.request;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.CommentEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description: 商品评价分页请求实体
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCommentRequestDTO", description = "商品评价分页请求实体")
public class PageCommentRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品id", required = true, position = 1)
    @NotNull(message = "商品id不能为空")
    private Long productId;

    @ApiModelProperty(value = "skuId", position = 5)
    private Long skuId;

    @ApiModelProperty(value = "评价级别", position = 6)
    @ValueInEnum(CommentEnum.class)
    private Integer commentLevel;

    @ApiModelProperty(value = "是否有图", position = 7)
    private Boolean hasPic;

}

package com.dmall.oms.api.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 评价请求实体
 * @author: created by hang.yu on 2020/4/12 15:49
 */
@Data
@ApiModel(value = "CommentRequestDTO", description = "评价请求实体")
public class CommentRequestDTO {

    @ApiModelProperty(value = "子订单号", position = 1)
    @NotNull(message = "子订单号不能为空")
    private Long subOrderId;

    @ApiModelProperty(value = "评价商品列表", position = 1)
    @NotNull(message = "评价商品列表不能为空")
    @Size(min = 1, message = "评价商品列表不能为空")
    @Valid
    private List<CommentSkuDTO> commentSkuList;
}

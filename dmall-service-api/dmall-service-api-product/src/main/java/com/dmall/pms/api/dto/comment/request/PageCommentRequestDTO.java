package com.dmall.pms.api.dto.comment.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 商品评价分页请求实体
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCommentRequestDTO", description = "商品评价分页请求实体")
public class PageCommentRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品id", position = 1)
    private Long productId;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "订单号", position = 3)
    private Long orderId;

    @ApiModelProperty(value = "子订单号", position = 4)
    private String subOrderId;

}

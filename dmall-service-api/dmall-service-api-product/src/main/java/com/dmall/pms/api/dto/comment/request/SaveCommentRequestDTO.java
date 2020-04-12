package com.dmall.pms.api.dto.comment.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增商品评价请求实体
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Data
@ApiModel(value = "SaveCommentRequestDTO", description = "新增商品评价请求实体")
public class SaveCommentRequestDTO implements Serializable {

    private static final long serialVersionUID = -7927056087634841761L;

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "订单号", position = 2)
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "子订单号", position = 3)
    @NotNull(message = "子订单号不能为空")
    private Long subOrderId;

    @ApiModelProperty(value = "评价内容", position = 4)
    @NotBlank(message = "评价内容不能为空")
    private String content;

    @ApiModelProperty(value = "评分 1-5", position = 5)
    @NotNull(message = "评分不能为空")
    private Integer star;

    @ApiModelProperty(value = "媒体对象 图片或视频,多个以逗号隔开", position = 6)
    private String medias;
}

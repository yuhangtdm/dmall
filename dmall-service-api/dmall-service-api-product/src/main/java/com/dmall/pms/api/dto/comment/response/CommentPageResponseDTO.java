package com.dmall.pms.api.dto.comment.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 商品评价响应实体
 * @author: created by hang.yu on 2020-04-12 15:31:43
 */
@Data
@ApiModel(value = "CommentPageResponseDTO", description = "商品评价分页响应实体")
public class CommentPageResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "子订单号", position = 3)
    private Long subOrderId;

    @ApiModelProperty(value = "评价内容", position = 4)
    private String content;

    @ApiModelProperty(value = "评分 1-5", position = 5)
    private Integer star;

    @ApiModelProperty(value = "媒体对象 图片或视频,多个以逗号隔开", position = 7)
    private String medias;

    @ApiModelProperty(value = "评价时间", position = 8)
    private Date createTime;

    @ApiModelProperty(value = "sku规格", position = 9)
    private String skuSpecificationsJson;

}

package com.dmall.oms.api.dto.commentdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 评价详情响应实体
 * @author: created by hang.yu on 2020/4/12 16:46
 */
@Data
@ApiModel(value = "CommentDetailResponseDTO", description = "评价详情响应实体")
public class CommentDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = -4884566657214529723L;

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku主图", position = 3)
    private String skuMainPic;

    @ApiModelProperty(value = "sku数量", position = 4)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku总价", position = 5)
    private BigDecimal skuTotalPrice;

    @ApiModelProperty(value = "评价", position = 6)
    private Integer star;

    @ApiModelProperty(value = "评价内容", position = 7)
    private String content;

    @ApiModelProperty(value = "评价时间", position = 8)
    private Date createTime;
}

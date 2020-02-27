package com.dmall.mms.api.dto.comment.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 商品评价列表请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListCommentRequestDTO", description = "商品评价列表请求实体")
public class ListCommentRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "商品id", position = 3)
        private Long productId;

        @ApiModelProperty(value = "skuid", position = 4)
        private Long skuId;

        @ApiModelProperty(value = "订单id", position = 5)
        private Long orderId;

        @ApiModelProperty(value = "订单项id", position = 6)
        private String orderItemId;

        @ApiModelProperty(value = "评价内容", position = 7)
        private String content;

        @ApiModelProperty(value = "评分 1-5", position = 8)
        private Integer star;

        @ApiModelProperty(value = "评价级别 1-好评;2-中评;3-差评", position = 9)
        private Integer level;

        @ApiModelProperty(value = "媒体对象 图片或视频,多个以逗号隔开", position = 10)
        private String medias;

        @ApiModelProperty(value = "会员昵称", position = 11)
        private String memberNickName;

        @ApiModelProperty(value = "评价ip", position = 12)
        private String memberIp;

        @ApiModelProperty(value = "会员头像", position = 13)
        private String memberIcon;

        @ApiModelProperty(value = "商品编号", position = 14)
        private String productNo;

        @ApiModelProperty(value = "sku编号", position = 15)
        private String skuNo;

        @ApiModelProperty(value = "订单编号", position = 16)
        private String orderNo;

        @ApiModelProperty(value = "订单项编号", position = 17)
        private String orderItemNo;







}

package com.dmall.pms.api.dto.comment.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商品评价公共响应实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonCommentResponseDTO", description = "商品评价公共响应实体")
public class CommonCommentResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "id", position = 1)
    private Long id;



    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;



    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;



    @ApiModelProperty(value = "订单id", position = 4)
    private Long orderId;



    @ApiModelProperty(value = "订单项id", position = 5)
    private String orderItemId;



    @ApiModelProperty(value = "评价内容", position = 6)
    private String content;



    @ApiModelProperty(value = "星级 1-5星，1-差评;2,3-中评;4,5-好评", position = 7)
    private Integer star;



    @ApiModelProperty(value = "评价级别 1-好评;2-中评;3-差评", position = 8)
    private Integer level;



    @ApiModelProperty(value = "媒体对象 图片或视频,多个以逗号隔开", position = 9)
    private String medias;



    @ApiModelProperty(value = "会员昵称", position = 10)
    private String memberNickName;



    @ApiModelProperty(value = "评价ip", position = 11)
    private String memberIp;



    @ApiModelProperty(value = "会员头像", position = 12)
    private String memberIcon;



    @ApiModelProperty(value = "是否显示在评论列表中 Y-显示;N-不显示", position = 13)
    private String showStatus;



    @ApiModelProperty(value = "点赞数", position = 14)
    private Integer praiseCount;



    @ApiModelProperty(value = "回复数", position = 15)
    private Integer replyCount;



    @ApiModelProperty(value = "商品编号", position = 16)
    private String productNo;



    @ApiModelProperty(value = "sku编号", position = 17)
    private String skuNo;



    @ApiModelProperty(value = "订单编号", position = 18)
    private String orderNo;



    @ApiModelProperty(value = "订单项编号", position = 19)
    private String orderItemNo;



    @ApiModelProperty(value = "创建人", position = 20)
    private Long creator;



    @ApiModelProperty(value = "创建时间", position = 21)
    private Date gmtCreated;



    @ApiModelProperty(value = "更新人", position = 22)
    private Long modifier;



    @ApiModelProperty(value = "更新时间", position = 23)
    private Date gmtModified;



    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 24)
    private String isDeleted;


}

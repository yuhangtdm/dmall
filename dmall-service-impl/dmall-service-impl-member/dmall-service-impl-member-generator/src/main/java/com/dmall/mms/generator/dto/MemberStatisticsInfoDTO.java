package com.dmall.mms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员统计信息表
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value="MemberStatisticsInfoDTO", description="会员统计信息表")
public class MemberStatisticsInfoDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;

    @ApiModelProperty(value = "累计消费金额", position = 3)
    private Integer consumeAmount;

    @ApiModelProperty(value = "订单数量", position = 4)
    private Integer orderCount;

    @ApiModelProperty(value = "优惠券数量", position = 5)
    private Integer couponCount;

    @ApiModelProperty(value = "评价数量", position = 6)
    private Integer commentCount;

    @ApiModelProperty(value = "退货数量", position = 7)
    private Integer returnOrderCount;

    @ApiModelProperty(value = "登录次数", position = 8)
    private Integer loginCount;

    @ApiModelProperty(value = "关注数量", position = 9)
    private Integer attendCount;

    @ApiModelProperty(value = "粉丝数量", position = 10)
    private Integer fansCount;

    @ApiModelProperty(value = "收藏商品数量", position = 11)
    private Integer collectProductCount;

    @ApiModelProperty(value = "收藏专题数量", position = 12)
    private Integer collectSubjectCount;

    @ApiModelProperty(value = "收藏店铺数量", position = 13)
    private Integer collectMerchantsCount;

    @ApiModelProperty(value = "创建人", position = 14)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 15)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 16)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 17)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 Y-可用;N-不可用", position = 18)
    private String isDeleted;

}

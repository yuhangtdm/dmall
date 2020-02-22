package com.dmall.mms.api.dto.memberstatisticsinfo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员统计信息分页请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMemberStatisticsInfoRequestDTO", description =  "会员统计信息分页请求实体")
public class PageMemberStatisticsInfoRequestDTO  extends PageRequestDTO {



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







}

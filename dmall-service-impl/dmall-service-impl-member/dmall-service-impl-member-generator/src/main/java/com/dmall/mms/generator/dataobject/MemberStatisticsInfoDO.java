package com.dmall.mms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员统计信息表
 * </p>
 *
 * @author hang.yu
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member_statistics_info")
public class MemberStatisticsInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 累计消费金额
     */
    @TableField("consume_amount")
    private Integer consumeAmount;

    /**
     * 订单数量
     */
    @TableField("order_count")
    private Integer orderCount;

    /**
     * 优惠券数量
     */
    @TableField("coupon_count")
    private Integer couponCount;

    /**
     * 评价数量
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 退货数量
     */
    @TableField("return_order_count")
    private Integer returnOrderCount;

    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;

    /**
     * 关注数量
     */
    @TableField("attend_count")
    private Integer attendCount;

    /**
     * 粉丝数量
     */
    @TableField("fans_count")
    private Integer fansCount;

    /**
     * 收藏商品数量
     */
    @TableField("collect_product_count")
    private Integer collectProductCount;

    /**
     * 收藏专题数量
     */
    @TableField("collect_subject_count")
    private Integer collectSubjectCount;

    /**
     * 收藏店铺数量
     */
    @TableField("collect_merchants_count")
    private Integer collectMerchantsCount;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 N-可用;Y-不可用
     */
    @TableField("is_deleted")
    private String isDeleted;


}

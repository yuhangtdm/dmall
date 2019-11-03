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
 * 会员-优惠券表
 * </p>
 *
 * @author yuhang
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mms_member_coupon_member")
public class MemberCouponMemberDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券id
     */
    @TableField("coupon_id")
    private String couponId;

    /**
     * 会员id
     */
    @TableField("member_id")
    private String memberId;

    /**
     * 使用状态 1-未使用;2-已使用;3-已过期
     */
    @TableField("use_status")
    private String useStatus;

    /**
     * 获取类型 1-后台赠送;2-自己领取
     */
    @TableField("get_type")
    private String getType;

    /**
     * 使用时间
     */
    @TableField("use_time")
    private Date useTime;

    /**
     * 使用的订单号
     */
    @TableField("order_no")
    private String orderNo;

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
     * 状态 Y-可用;N-不可用
     */
    @TableField("is_deleted")
    private String isDeleted;


}

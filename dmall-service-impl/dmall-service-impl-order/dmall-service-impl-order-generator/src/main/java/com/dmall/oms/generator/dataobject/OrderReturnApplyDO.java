package com.dmall.oms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 退换货申请表
 * @author: created by hang.yu on 2020-03-12 23:12:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_order_return_apply")
public class OrderReturnApplyDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单项id
     */
    private Long orderItemId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 申请原因id
     */
    private Long reasonId;


    /**
     * 类型 1-仅退款;2-退货;3-换货
     */
    private Integer type;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku主图
     */
    private String skuPic;

    /**
     * 申请数量
     */
    private Integer number;

    /**
     * sku单价
     */
    private BigDecimal price;

    /**
     * 订单项实际总价
     */
    private BigDecimal skuPrice;

    /**
     * 申请金额 订单项实际总价/申请数量
     */
    private BigDecimal amount;

    /**
     * 状态 1-待处理;2-退/换货中;3-已完成;4-已拒绝;5-已取消
     */
    private Integer status;

    /**
     * 申请描述
     */
    private String description;

    /**
     * 申请凭证 上传凭证照片,多张以逗号隔开
     */
    private String applyCredentials;

    /**
     * 处理备注
     */
    private String handleNote;

    /**
     * 处理人员
     */
    private Long handleMan;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 退款方式 1-原返
     */
    private Integer refundType;

    /**
     * 取件地址 满足有快递人员上门取件的场景
     */
    private String pickUpAddress;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifier;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 状态 Y,可用;N:不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}

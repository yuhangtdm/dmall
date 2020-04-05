package com.dmall.oms.generator.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 子订单表 
 * @author: created by hang.yu on 2020-04-04 15:41:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oms_sub_order")
public class SubOrderDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父订单id
     */
    private Long orderId;

    /**
     * 订单明细id
     */
    private Long orderItemId;

    /**
     * 发货状态 Y-已发货;N-未发货
     */
    private String deliverStatus;

    // todo 快递信息可考虑分表
    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 快递费
     */
    private BigDecimal expressFee;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 发货人id
     */
    private Long deliverId;

    /**
     * 发货人姓名
     */
    private String deliverName;

    /**
     * 发货人电话
     */
    private String deliverPhone;

    /**
     * 发货省份/直辖市
     */
    private String deliverProvince;

    /**
     * 发货城市
     */
    private String deliverCity;

    /**
     * 发货人区/县
     */
    private String deliverRegion;

    /**
     * 发货详细地址
     */
    private String deliverDetailAddress;

    /**
     * 发货时间
     */
    private Date deliverTime;

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
     * 状态 N-可用;Y-不可用
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private String isDeleted;


}

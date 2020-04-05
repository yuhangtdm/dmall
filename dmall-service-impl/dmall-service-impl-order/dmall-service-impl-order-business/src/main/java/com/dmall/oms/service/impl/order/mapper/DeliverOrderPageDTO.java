package com.dmall.oms.service.impl.order.mapper;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 发货分页DTO
 * @author: created by hang.yu on 2020/4/5 11:32
 */
@Data
public class DeliverOrderPageDTO implements Serializable {

    private static final long serialVersionUID = -5678489345414084186L;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 子订单号
     */
    private Long subOrderId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 发货状态
     */
    private String deliverStatus;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人省份/直辖市
     */
    private String receiverProvince;

    /**
     * 收货人城市
     */
    private String receiverCity;

    /**
     * 收货人区/县
     */
    private String receiverRegion;

    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 发货人id
     */
    private Long deliverId;

    /**
     * 发货人名称
     */
    private String deliverName;
}

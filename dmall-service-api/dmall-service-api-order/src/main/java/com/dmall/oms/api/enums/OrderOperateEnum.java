package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 订单操作枚举
 * @author: created by hang.yu on 2020/4/5 21:38
 */
@Getter
@AllArgsConstructor
public enum OrderOperateEnum implements CodeDescEnum<Integer> {
    /**
     * 创建订单
     */
    CREATE(1, "创建订单"),

    /**
     * 取消订单
     */
    CANCEL(2, "取消订单"),

    /**
     * 删除订单
     */
    DELETE(3, "删除订单"),

    /**
     * 支付订单
     */
    PAY(4, "支付订单"),

    /**
     *
     */
    SPLIT(5, "拆单"),

    /**
     * 发货
     */
    DELIVER(6, "发货"),

    /**
     * 确认收货
     */
    RECEIVER(7, "确认收货"),

    /**
     * 申请退货
     */
    RETURN(8, "申请退货"),

    /**
     * 申请换货
     */
    CHANGE(9, "申请换货"),

    /**
     * 退换货审批
     */
    APPROVAL(10, "退换货审批"),

    /**
     * 退款
     */
    REFUND(11, "退款"),

    /**
     * 订单备注
     */
    REMARK(12, "订单备注"),

    /**
     * 评价
     */
    COMMENT(13, "评价"),


    ;

    private Integer code;

    private String desc;
}

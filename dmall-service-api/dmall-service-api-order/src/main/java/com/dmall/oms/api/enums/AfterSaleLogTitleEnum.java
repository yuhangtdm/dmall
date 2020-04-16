package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 售后日志标题枚举
 * @author: created by hang.yu on 2020/4/16 22:00
 */
@Getter
@AllArgsConstructor
public enum AfterSaleLogTitleEnum implements CodeDescEnum<Integer> {

    /**
     * 申请退款
     */
    APPLY_REFUND(1, "申请退款"),

    /**
     * 申请退货
     */
    APPLY_RETURN(2, "申请退货"),

    /**
     * 审核意见
     */
    APPROVAL(3, "审核意见"),

    /**
     * 填写物流单号
     */
    WRITE_LOGISTICS_NO(4, "填写物流单号"),

    /**
     * 卖家已收到商品
     */
    PRODUCT_RECEIVED(5, "卖家已收到商品"),

    /**
     * 退款成功
     */
    REFUND_SUCCESS(6, "退款成功"),

    /**
     * 关闭服务单
     */
    CLOSED(7, "关闭服务单"),

    /**
     * 删除服务单
     */
    DELETED(8, "删除服务单"),

    ;

    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;
}

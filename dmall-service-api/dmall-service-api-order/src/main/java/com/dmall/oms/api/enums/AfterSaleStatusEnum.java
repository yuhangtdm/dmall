package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 售后状态枚举
 * @author: created by hang.yu on 2020/4/14 22:18
 */
@Getter
@AllArgsConstructor
public enum AfterSaleStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 已申请
     */
    APPLY(1, "已申请"),

    /**
     * 退/换货中
     */
    RE_PROGRESS(2, "退/换货中"),

    /**
     * 退款中
     */
    REFUND_PROGRESS(3, "退款中"),

    /**
     * 退款完成
     */
    COMPLETED(4, "退款完成"),

    /**
     * 已拒绝
     */
    REFUSE(5, "已拒绝"),

    /**
     * 已关闭
     */
    CLOSED(6, "已关闭"),
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

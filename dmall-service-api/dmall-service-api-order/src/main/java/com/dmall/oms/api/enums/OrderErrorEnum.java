package com.dmall.oms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 创建订单枚举
 * @author: created by hang.yu on 2020/3/28 16:06
 */
@Getter
@AllArgsConstructor
public enum OrderErrorEnum implements ErrorCodeEnum {

    SKU_PRICE_CHANGE("1001","价格发生改变,请重新下单"),

    SKU_TOTAL_MONEY_CHANGE("1002","商品总价不允许修改,请重新下单"),

    FREIGHT_CHANGE("1003","运费不允许修改,请重新下单"),

    ORDER_MONEY_CHANGE("1004","总价不允许修改,请重新下单"),

    INSUFFICIENT_INVENTORY("1005","很抱歉,库存不足"),

    SUBMIT_REPEAT("1006","您的订单已提交"),

    ORDER_NOT_EXISTS("1007","您的订单不存在"),

    CANCEL_STATUS_ERROR("1008","只可取消待支付的订单"),

    DELETE_STATUS_ERROR("1008","只可删除已取消或已完成的订单"),


    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}

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

    SKU_PRICE_CHANGE("1001", "价格发生改变,请重新下单"),

    SKU_TOTAL_PRICE_CHANGE("1002", "商品总价不允许修改,请重新下单"),

    FREIGHT_CHANGE("1003", "运费不允许修改,请重新下单"),

    ORDER_PRICE_CHANGE("1004", "总价不允许修改,请重新下单"),

    INSUFFICIENT_INVENTORY("1005", "很抱歉,库存不足"),

    SUBMIT_REPEAT("1006", "您的订单已提交"),

    ORDER_NOT_EXISTS("2000", "您的订单不存在"),

    NO_AUTHORITY("2001", "您无权操作他人的订单"),

    CANCEL_STATUS_ERROR("3000", "只可取消待支付的订单"),

    DELETE_STATUS_ERROR("3001", "只可删除已取消或已完成的订单"),

    AFTER_SALE_ING("3002", "售后中的单子不可删除"),

    SPLITED("4000", "订单已拆分"),

    SPLIT_WAY_ERROR("4001", "拆单方式不合法"),

    SPLIT_DETAIL_NOT_EMPTY("4002", "拆单明细不能为空"),

    WARE_HOUSE_ID_NOT_EMPTY("4003", "仓库id不能为空"),


    SUB_ORDER_NOT_EXISTS("5000", "子订单不存在"),

    DELIVER_PERSON_WAREHOUSE_EMPTY("5001","发货员的仓库不能为空"),

    RECEIVER_STATUS("6000","您当前不可确认收货"),

    NOT_COMMENT("7000","您当前进评价页"),

    COMMENT_ERROR("7001","很抱歉，您当前没有评价资格，请联系商城客服反馈"),

    COMMENT_DETAIL_ERROR("7002","很抱歉，您当前不可查看评价"),

    APPLY_REFUND_ERROR("8000","很抱歉，您当前不可申请退款"),

    AFTER_SALE_NOT_EXISTS("9000","该售后单不存在"),

    AFTER_SALE_APPROVAL("9001","该售后单当前状态不可操作"),

    AFTER_SALE_CLOSED("9002","该售后单不可关闭"),

    AFTER_SALE_DELETE("9002","该售后单不可删除"),

    CREATE_JOB_ERROR("9003","创建自动收货的job失败"),

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

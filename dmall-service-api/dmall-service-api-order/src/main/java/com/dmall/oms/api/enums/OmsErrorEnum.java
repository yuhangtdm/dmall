package com.dmall.oms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 创建订单枚举 1500开头
 * @author: created by hang.yu on 2020/3/28 16:06
 */
@Getter
@AllArgsConstructor
public enum OmsErrorEnum implements ErrorCodeEnum {

    /**
     * 价格发生改变,请重新下单
     */
    SKU_PRICE_CHANGE("1500", "价格发生改变,请重新下单"),

    /**
     * 商品总价不允许修改,请重新下单
     */
    SKU_TOTAL_PRICE_CHANGE("1501", "商品总价不允许修改,请重新下单"),

    /**
     * 运费不允许修改,请重新下单
     */
    FREIGHT_CHANGE("1502", "运费不允许修改,请重新下单"),

    /**
     * 总价不允许修改,请重新下单
     */
    ORDER_PRICE_CHANGE("1503", "总价不允许修改,请重新下单"),

    /**
     * 很抱歉,库存不足
     */
    INSUFFICIENT_INVENTORY("1504", "很抱歉,库存不足"),

    /**
     * 您的订单已提交
     */
    SUBMIT_REPEAT("1505", "您的订单已提交,请勿重新提交"),

    /**
     * 您的订单不存在
     */
    ORDER_NOT_EXISTS("1506", "您的订单不存在"),

    /**
     * 您无权操作他人的订单
     */
    NO_AUTHORITY("1507", "您无权操作他人的订单"),

    /**
     * 只可取消待支付的订单
     */
    CANCEL_STATUS_ERROR("1508", "只可取消待支付的订单"),

    /**
     * 只可删除已取消或已完成的订单
     */
    DELETE_STATUS_ERROR("1509", "只可删除已取消或已完成的订单"),

    /**
     * 售后中的单子不可删除
     */
    AFTER_SALE_ING("1510", "售后中的单子不可删除"),

    /**
     * 订单已拆分
     */
    SPLIT("1511", "订单已拆分"),

    /**
     * 拆单方式不合法
     */
    SPLIT_WAY_ERROR("1512", "拆单方式不合法"),

    /**
     * 拆单明细不能为空
     */
    SPLIT_DETAIL_NOT_EMPTY("1513", "拆单明细不能为空"),

    /**
     * 仓库id不能为空
     */
    WARE_HOUSE_ID_NOT_EMPTY("1514", "仓库id不能为空"),

    /**
     * 子订单不存在
     */
    SUB_ORDER_NOT_EXISTS("1515", "子订单不存在"),

    /**
     * 发货员的仓库不能为空
     */
    DELIVER_PERSON_WAREHOUSE_EMPTY("1516", "发货员的仓库不能为空"),

    /**
     * 您当前不可确认收货
     */
    RECEIVER_STATUS("1517", "您当前不可确认收货"),

    /**
     * 您当前进评价页
     */
    NOT_COMMENT("1518", "您当前进评价页"),

    /**
     * 很抱歉，您当前没有评价资格，请联系商城客服反馈
     */
    COMMENT_ERROR("1519", "很抱歉，您当前没有评价资格，请联系商城客服反馈"),

    /**
     * 很抱歉，您当前不可查看评价
     */
    COMMENT_DETAIL_ERROR("1520", "很抱歉，您当前不可查看评价"),

    /**
     * 很抱歉，您当前不可申请退款
     */
    APPLY_REFUND_ERROR("1521", "很抱歉，您当前不可申请退款"),

    /**
     * 该售后单不存在
     */
    AFTER_SALE_NOT_EXISTS("1522", "该售后单不存在"),

    /**
     * 该售后单当前状态不可操作
     */
    AFTER_SALE_APPROVAL("1523", "该售后单当前状态不可操作"),

    /**
     * 该售后单不可关闭
     */
    AFTER_SALE_CLOSED("1524", "该售后单不可关闭"),

    /**
     * 该售后单不可删除
     */
    AFTER_SALE_DELETE("1525", "该售后单不可删除"),

    /**
     * 创建自动收货的job失败
     */
    CREATE_JOB_ERROR("1526", "创建自动收货的job失败"),

    /**
     * 订单项不存在
     */
    ORDER_ITEM_NOT_EXISTS("1527","订单项不存在"),

    ;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;

}

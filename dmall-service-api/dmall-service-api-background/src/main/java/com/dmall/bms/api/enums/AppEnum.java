package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: AppEnum
 * @author: created by hang.yu on 2020/5/11 20:40
 */
@Getter
@AllArgsConstructor
public enum AppEnum implements CodeDescEnum<String> {
    /**
     * DMALL_SERVICE_IMPL_BACKGROUND
     */
    DMALL_SERVICE_IMPL_BACKGROUND("dmall-service-impl-background", "后台服务"),

    /**
     * DMALL_SERVICE_IMPL_CART
     */
    DMALL_SERVICE_IMPL_CART("dmall-service-impl-cart", "购物车服务"),

    /**
     * DMALL_SERVICE_IMPL_MEMBER
     */
    DMALL_SERVICE_IMPL_MEMBER("dmall-service-impl-member", "会员服务"),

    /**
     * DMALL_SERVICE_IMPL_ORDER
     */
    DMALL_SERVICE_IMPL_ORDER("dmall-service-impl-order", "订单服务"),

    /**
     * DMALL_SERVICE_IMPL_PAY
     */
    DMALL_SERVICE_IMPL_PAY("dmall-service-impl-pay", "支付服务"),

    /**
     * DMALL_SERVICE_IMPL_PRODUCT
     */
    DMALL_SERVICE_IMPL_PRODUCT("dmall-service-impl-product", "商品服务"),

    /**
     * DMALL_SERVICE_IMPL_SEARCH
     */
    DMALL_SERVICE_IMPL_SEARCH("dmall-service-impl-search", "搜索服务"),

    /**
     * DMALL_SERVICE_IMPL_SSO
     */
    DMALL_SERVICE_IMPL_SSO("dmall-service-impl-sso", "sso服务");

    private final String code;

    private final String desc;
}

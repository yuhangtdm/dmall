package com.dmall.cart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.cart.api.enums.OperateEnum;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.service.impl.dto.CartDTO;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.CookieUtil;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * @description: 购物车工具类
 * @author: created by hang.yu on 2020/3/14 17:18
 */
public class AddCartUtil {

    public static final String COOKIE_NAME = "cart";

    public static final Integer COOKIE_STORE_TIME = 60 * 60 * 24;

    private AddCartUtil() {}

    /**
     * 未登录时添加购物车
     */
    public static void notLoginAddCart(Integer number, OperateEnum operateEnum, BasicSkuResponseDTO skuData) {
        // 查询cookie中的购物车数据
        String cartJson = CookieUtil.getCookie(RequestUtil.getRequest(), AddCartUtil.COOKIE_NAME, true);
        if (StrUtil.isNotBlank(cartJson)) {
            List<CartDTO> cartDTOS = JsonUtil.fromJson(cartJson, new TypeReference<List<CartDTO>>() {});
            Optional<CartDTO> first = cartDTOS.stream()
                .filter(cartDTO -> cartDTO.getSkuId().equals(skuData.getId()))
                .findFirst();
            if (first.isPresent()) {
                CartDTO cartDTO = first.get();
                // 取最新的价格
                cartDTO.setPrice(skuData.getPrice());
                cartDTO.setGmtModified(new DateTime());
                cartDTO.setNumber(operateEnum == OperateEnum.ADD ? (number + cartDTO.getNumber()) : number);
                cartDTO.setTotalPrice(NumberUtil.mul(cartDTO.getNumber(), cartDTO.getPrice()));
                CookieUtil.addCookie(ResponseUtil.getResponse(), AddCartUtil.COOKIE_NAME,
                    JsonUtil.toJson(cartDTOS), AddCartUtil.COOKIE_STORE_TIME, true);
            } else {
                addCookieCart(number, skuData, cartDTOS);
            }
        } else {
            addCookieCart(number, skuData, Lists.newArrayList());
        }
    }

    /**
     * 已登录时添加购物车
     */
    public static CartItemDO loginAddCart(Integer number, OperateEnum operateEnum, BasicSkuResponseDTO skuData,
        PortalMemberDTO login, List<CartItemDO> cartItemDOS) {
        // 会员已登录
        if (CollUtil.isNotEmpty(cartItemDOS)) {
            Optional<CartItemDO> first = cartItemDOS.stream()
                .filter(cartItem -> cartItem.getSkuId().equals(skuData.getId()))
                .findFirst();
            if (first.isPresent()) {
                CartItemDO cartItemDO = first.get();
                cartItemDO.setNumber(operateEnum == OperateEnum.ADD ? (cartItemDO.getNumber() + number) : number);
                cartItemDO.setSkuPrice(skuData.getPrice());
                cartItemDO.setSkuTotalPrice(NumberUtil.mul(skuData.getPrice(), number));
                return cartItemDO;
            } else {
                return getInsertCartDb(number, skuData, login);
            }
        } else {
            return getInsertCartDb(number, skuData, login);
        }
    }

    /**
     * 添加cookie
     */
    private static void addCookieCart(Integer number, BasicSkuResponseDTO skuData, List<CartDTO> cartDTOS) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setSkuId(skuData.getId());
        cartDTO.setPrice(skuData.getPrice());
        cartDTO.setNumber(number);
        cartDTO.setTotalPrice(NumberUtil.mul(cartDTO.getNumber(), cartDTO.getPrice()));
        cartDTO.setChecked(false);
        cartDTOS.add(cartDTO);
        CookieUtil.addCookie(ResponseUtil.getResponse(), AddCartUtil.COOKIE_NAME, JsonUtil.toJson(cartDTOS),
            AddCartUtil.COOKIE_STORE_TIME, true);
    }

    /**
     * 插入表
     */
    private static CartItemDO getInsertCartDb(Integer number, BasicSkuResponseDTO skuData, PortalMemberDTO login) {
        CartItemDO cartItemDO = new CartItemDO();
        cartItemDO.setSkuId(skuData.getId());
        cartItemDO.setProductId(skuData.getProductId());
        cartItemDO.setMemberId(login.getId());
        cartItemDO.setNumber(number);
        cartItemDO.setSkuTotalPrice(skuData.getPrice());
        cartItemDO.setSkuTotalPrice(NumberUtil.mul(skuData.getPrice(), cartItemDO.getNumber()));
        cartItemDO.setHasStock(skuData.getStock() > 0 ? YNEnum.Y.getCode() : YNEnum.N.getCode());
        cartItemDO.setChecked(YNEnum.N.getCode());
        return cartItemDO;
    }
}

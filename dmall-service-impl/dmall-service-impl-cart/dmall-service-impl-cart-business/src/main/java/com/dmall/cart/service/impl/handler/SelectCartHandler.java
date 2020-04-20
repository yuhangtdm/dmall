package com.dmall.cart.service.impl.handler;

import cn.hutool.json.JSON;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.select.SelectCartRequestDTO;
import com.dmall.cart.api.dto.select.SelectTypeEnum;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.cart.service.impl.cache.CartCacheService;
import com.dmall.cart.service.impl.cache.Constants;
import com.dmall.cart.service.impl.dto.CartDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.CookieUtil;
import com.dmall.common.util.JsonUtil;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 勾选或取消勾选商品
 * @author: created by hang.yu on 2020/3/14 18:11
 */
@Component
public class SelectCartHandler extends AbstractCommonHandler<SelectCartRequestDTO, CartItemDO, CartListResponseDTO> {

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private ListCartHandler listCartHandler;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public BaseResult<CartListResponseDTO> processor(SelectCartRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO login = PortalMemberContextHolder.get();
        if (login == null) {
            notLoginUpdateCart(requestDTO.getType(), requestDTO.getSkuIds());
        } else {
            loginUpdateCart(requestDTO.getType(), requestDTO.getSkuIds(), login.getId());
        }
        return listCartHandler.handler(null);
    }


    /**
     * 未登录时修改购物车
     */
    private void notLoginUpdateCart(Integer type, List<Long> skuIds) {
        // 查询cookie中的购物车数据
        String cartJson = CookieUtil.getCookie(RequestUtil.getRequest(), Constants.COOKIE_NAME, true);
        List<CartDTO> cartDTOS = JsonUtil.fromJson(cartJson, new TypeReference<List<CartDTO>>() {});

        for (CartDTO cartDTO : cartDTOS) {
            if (skuIds.contains(cartDTO.getSkuId())) {
                cartDTO.setChecked(type.equals(SelectTypeEnum.CHECK.getCode()));
            }
        }
        CookieUtil.addCookie(ResponseUtil.getResponse(), Constants.COOKIE_NAME, JsonUtil.toJson(cartDTOS), Constants.COOKIE_STORE_TIME, true);
    }

    /**
     * 登录后修改购物车
     */
    private void loginUpdateCart(Integer type, List<Long> skuIds, Long memberId) {
        List<CartItemDO> cartItemDOS = cartCacheService.list(memberId);
        for (CartItemDO cartItemDO : cartItemDOS) {
            if (skuIds.contains(cartItemDO.getSkuId())) {
                cartItemDO.setChecked(type.equals(SelectTypeEnum.CHECK.getCode()) ? YNEnum.Y.getCode() : YNEnum.N.getCode());
                cartCacheService.insert(memberId, cartItemDO);
                cartItemMapper.updateById(cartItemDO);
            }
        }
    }

}

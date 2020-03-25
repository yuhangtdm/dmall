package com.dmall.cart.service.impl.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.cart.service.impl.cache.CartCacheService;
import com.dmall.cart.service.impl.cache.Constants;
import com.dmall.cart.service.impl.dto.CartDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.CookieUtil;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除购物车处理器
 * @author: created by hang.yu on 2020/3/14 22:32
 */
@Component
public class DeleteCartHandler extends AbstractCommonHandler<DeleteCartRequestDTO, CartItemDO, CartListResponseDTO> {

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ListCartHandler listCartHandler;

    @Override
    public BaseResult<CartListResponseDTO> processor(DeleteCartRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO login = PortalMemberContextHolder.get();
        if (login == null) {
            notLoginDeleteCart(requestDTO.getSkuIds());
        } else {
            loginDeleteCart(login.getId(), requestDTO.getSkuIds());
        }
        return listCartHandler.handler(null);
    }

    /**
     * 未登录时删除购物车
     */
    private void notLoginDeleteCart(List<Long> skuIds) {
        // 查询cookie中的购物车数据
        String cartJson = CookieUtil.getCookie(RequestUtil.getRequest(), Constants.COOKIE_NAME, true);
        if (StrUtil.isNotBlank(cartJson)) {
            List<CartDTO> cartDTOS = JSON.parseArray(cartJson, CartDTO.class);
            cartDTOS.removeIf(next -> skuIds.contains(next.getSkuId()));
            CookieUtil.addCookie(ResponseUtil.getResponse(), Constants.COOKIE_NAME, JSON.toJSONString(cartDTOS), Constants.COOKIE_STORE_TIME, true);
        }
    }

    /**
     * 已登录后删除购物车
     */
    private void loginDeleteCart(Long memberId, List<Long> skuIds) {
        cartCacheService.delete(memberId, skuIds);
        cartItemMapper.delete(Wrappers.<CartItemDO>lambdaQuery().eq(CartItemDO::getMemberId, memberId)
                .in(CartItemDO::getSkuId, skuIds));
    }
}

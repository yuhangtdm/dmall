package com.dmall.cart.service.impl.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.list.CartSkuResponseDTO;
import com.dmall.cart.feign.SkuFeign;
import com.dmall.cart.generator.dataobject.CartItemDO;
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
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 购物车列表处理器
 * @author: created by hang.yu on 2020/3/14 15:57
 */
@Component
public class ListCartHandler extends AbstractCommonHandler<Void, CartItemDO, CartListResponseDTO> {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartCacheService cartCacheService;

    @Override
    public BaseResult<CartListResponseDTO> processor(Void aVoid) {
        // 判断当前会员是否登录
        PortalMemberDTO login = PortalMemberContextHolder.get();
        CartListResponseDTO cartListResponseDTO = new CartListResponseDTO();
        if (login == null) {
            return getCartListFromCookie(cartListResponseDTO);
        } else {
            return getCartListFromDb(login, cartListResponseDTO);
        }
    }

    private BaseResult<CartListResponseDTO> getCartListFromCookie(CartListResponseDTO cartListResponseDTO) {
        String cookie = CookieUtil.getCookie(RequestUtil.getRequest(), Constants.COOKIE_NAME, true);
        if (StrUtil.isBlank(cookie)) {
            return ResultUtil.success();
        }
        List<CartDTO> cartDTOS = JsonUtil.fromJson(cookie, new TypeReference<List<CartDTO>>() {});

        List<Long> skuIds = cartDTOS.stream().map(CartDTO::getSkuId).collect(Collectors.toList());
        BaseResult<List<BasicSkuResponseDTO>> basic = skuFeign.getBasic(skuIds);
        if (!basic.getResult()) {
            return ResultUtil.fail(basic.getCode(), basic.getMsg());
        }
        List<BasicSkuResponseDTO> skuResponseDTOList = basic.getData();
        List<CartSkuResponseDTO> skuList = Lists.newArrayList();
        for (CartDTO cartDTO : cartDTOS) {
            CartSkuResponseDTO cartSkuResponseDTO = new CartSkuResponseDTO();
            cartSkuResponseDTO.setSkuId(cartDTO.getSkuId());

            Optional<BasicSkuResponseDTO> any = skuResponseDTOList.stream()
                    .filter(basicData -> basicData.getId().equals(cartDTO.getSkuId()))
                    .findAny();
            if (any.isPresent()) {
                BasicSkuResponseDTO data = any.get();
                cartSkuResponseDTO.setSkuName(data.getName());
                cartSkuResponseDTO.setSkuSpecificationsJson(data.getSkuSpecificationsJson());
                cartSkuResponseDTO.setNumber(cartDTO.getNumber());
                if (NumberUtil.isLess(data.getPrice(), cartDTO.getPrice())) {
                    cartSkuResponseDTO.setHasReductionPrice(true);
                    cartSkuResponseDTO.setReductionPrice(NumberUtil.sub(cartDTO.getPrice(), data.getPrice()));
                } else {
                    cartSkuResponseDTO.setHasReductionPrice(false);
                }
                cartSkuResponseDTO.setSkuPrice(data.getPrice());
                cartSkuResponseDTO.setSkuTotalPrice(NumberUtil.mul(data.getPrice(), cartDTO.getNumber()));
                cartSkuResponseDTO.setHasStock(data.getStock() > 0);
                cartSkuResponseDTO.setStatus(data.getPublishStatus() == YNEnum.Y);
                cartSkuResponseDTO.setChecked(cartDTO.getChecked());
                skuList.add(cartSkuResponseDTO);
            }
        }
        cartListResponseDTO.setCarts(skuList);
        Integer totalNumber = skuList.stream().mapToInt(CartSkuResponseDTO::getNumber).sum();
        Integer totalCheckedNumber = skuList.stream()
                .filter(CartSkuResponseDTO::getChecked)
                .mapToInt(CartSkuResponseDTO::getNumber).sum();
        BigDecimal totalPrice = skuList.stream()
                .map(CartSkuResponseDTO::getSkuTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCheckedPrice = skuList.stream()
                .filter(CartSkuResponseDTO::getChecked)
                .map(CartSkuResponseDTO::getSkuTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cartListResponseDTO.setTotalNumber(totalNumber);
        cartListResponseDTO.setCheckTotalNumber(totalCheckedNumber);
        cartListResponseDTO.setTotalPrice(totalPrice);
        cartListResponseDTO.setCheckTotalPrice(totalCheckedPrice);
        cartListResponseDTO.setSkuNumber(skuList.size());
        return ResultUtil.success(cartListResponseDTO);
    }

    private BaseResult<CartListResponseDTO> getCartListFromDb(PortalMemberDTO login, CartListResponseDTO cartListResponseDTO) {
        List<CartItemDO> list = cartCacheService.list(login.getId());
        if (CollUtil.isEmpty(list)) {
            return ResultUtil.success();
        }
        List<Long> skuIds = list.stream().map(CartItemDO::getSkuId).collect(Collectors.toList());
        BaseResult<List<BasicSkuResponseDTO>> basic = skuFeign.getBasic(skuIds);
        if (!basic.getResult()) {
            return ResultUtil.fail(basic.getCode(), basic.getMsg());
        }
        List<BasicSkuResponseDTO> skuResponseDTOList = basic.getData();
        List<CartSkuResponseDTO> skuList = Lists.newArrayList();
        for (CartItemDO cartItemDO : list) {
            CartSkuResponseDTO cartSkuResponseDTO = new CartSkuResponseDTO();
            cartSkuResponseDTO.setSkuId(cartItemDO.getSkuId());
            Optional<BasicSkuResponseDTO> any = skuResponseDTOList.stream()
                    .filter(basicData -> basicData.getId().equals(cartItemDO.getSkuId()))
                    .findAny();
            if (any.isPresent()) {
                BasicSkuResponseDTO data = any.get();
                cartSkuResponseDTO.setSkuName(data.getName());
                cartSkuResponseDTO.setSkuSpecificationsJson(data.getSkuSpecificationsJson());
                cartSkuResponseDTO.setNumber(cartItemDO.getNumber());
                if (NumberUtil.isLess(data.getPrice(), cartItemDO.getSkuTotalPrice())) {
                    cartSkuResponseDTO.setHasReductionPrice(true);
                    cartSkuResponseDTO.setReductionPrice(NumberUtil.sub(cartItemDO.getSkuTotalPrice(), data.getPrice()));
                } else {
                    cartSkuResponseDTO.setHasReductionPrice(false);
                }
                cartSkuResponseDTO.setSkuPrice(data.getPrice());
                cartSkuResponseDTO.setSkuTotalPrice(NumberUtil.mul(data.getPrice(), cartItemDO.getNumber()));
                cartSkuResponseDTO.setHasStock(data.getStock() > 0);
                cartSkuResponseDTO.setStatus(data.getPublishStatus() == YNEnum.Y);
                skuList.add(cartSkuResponseDTO);
            }
        }
        cartListResponseDTO.setCarts(skuList);
        Integer totalNumber = skuList.stream().mapToInt(CartSkuResponseDTO::getNumber).sum();
        BigDecimal totalPrice = skuList.stream()
                .map(CartSkuResponseDTO::getSkuTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cartListResponseDTO.setTotalNumber(totalNumber);
        cartListResponseDTO.setTotalPrice(totalPrice);
        return ResultUtil.success(cartListResponseDTO);
    }

}

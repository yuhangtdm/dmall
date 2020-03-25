package com.dmall.cart.service.impl.handler;

import cn.hutool.core.util.NumberUtil;
import com.dmall.cart.api.dto.add.AddCartRequestDTO;
import com.dmall.cart.api.dto.add.AddCartResponseDTO;
import com.dmall.cart.feign.SkuFeign;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.cart.service.impl.AddCartUtil;
import com.dmall.cart.service.impl.cache.CartCacheService;
import com.dmall.cart.service.impl.enums.OperateEnum;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 新增购物车处理器
 * @author: created by hang.yu on 2020-03-11 22:46:56
 */
@Component
public class AddCartHandler extends AbstractCommonHandler<AddCartRequestDTO, CartItemDO, AddCartResponseDTO> {

    @Autowired
    private CartItemMapper cartMapper;

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private SkuFeign skuFeign;

    @Override
    public BaseResult<AddCartResponseDTO> processor(AddCartRequestDTO requestDTO) {
        BaseResult<List<BasicSkuResponseDTO>> basic = skuFeign.getBasic(Lists.newArrayList(requestDTO.getSkuId()));
        if (!basic.getResult()) {
            return ResultUtil.fail(basic.getCode(), basic.getMsg());
        }
        BasicSkuResponseDTO skuData = basic.getData().get(0);
        // 获取当前登录用户
        PortalMemberDTO login = PortalMemberContextHolder.get();
        if (login == null) {
            AddCartUtil.notLoginAddCart(requestDTO.getNumber(), OperateEnum.ADD, skuData);
        } else {
            List<CartItemDO> cartItemDOS = cartCacheService.list(login.getId());
            CartItemDO cartItemDO = AddCartUtil.loginAddCart(requestDTO.getNumber(), OperateEnum.ADD, skuData, login, cartItemDOS);
            handlerCart(login, cartItemDO);
        }
        return ResultUtil.success(getAddCartResponse(requestDTO, skuData));
    }

    private void handlerCart(PortalMemberDTO login, CartItemDO cartItemDO) {
        if (cartItemDO.getId() != null) {
            cartMapper.updateById(cartItemDO);
        } else {
            cartMapper.insert(cartItemDO);
        }
        cartCacheService.insert(login.getId(), cartItemDO);
    }

    private AddCartResponseDTO getAddCartResponse(AddCartRequestDTO requestDTO, BasicSkuResponseDTO skuData) {
        AddCartResponseDTO responseDTO = new AddCartResponseDTO();
        responseDTO.setSkuId(skuData.getId());
        responseDTO.setSkuName(skuData.getName());
        responseDTO.setSkuSpecificationsJson(skuData.getSkuSpecificationsJson());
        responseDTO.setNumber(requestDTO.getNumber());
        responseDTO.setPrice(skuData.getPrice());
        responseDTO.setTotalPrice(NumberUtil.mul(skuData.getPrice(), requestDTO.getNumber()));
        return responseDTO;
    }

}

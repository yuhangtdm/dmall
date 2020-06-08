package com.dmall.cart.service.impl.handler;

import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.update.UpdateNumberRequestDTO;
import com.dmall.cart.feign.SkuFeign;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.cart.service.impl.AddCartUtil;
import com.dmall.cart.service.impl.cache.CartCacheService;
import com.dmall.cart.api.enums.OperateEnum;
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
 * @description: 修改数量处理器
 * @author: created by hang.yu on 2020/3/14 17:13
 */
@Component
public class UpdateNumberHandler
    extends AbstractCommonHandler<UpdateNumberRequestDTO, CartItemDO, CartListResponseDTO> {

    @Autowired
    private ListCartHandler listCartHandler;

    @Autowired
    private CartItemMapper cartMapper;

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private SkuFeign skuFeign;

    @Override
    public BaseResult<CartListResponseDTO> processor(UpdateNumberRequestDTO requestDTO) {
        BaseResult<List<BasicSkuResponseDTO>> basic = skuFeign.getBasic(Lists.newArrayList(requestDTO.getSkuId()));
        if (!basic.getResult()) {
            return ResultUtil.fail(basic.getCode(), basic.getMsg());
        }
        BasicSkuResponseDTO skuData = basic.getData().get(0);
        // 获取当前登录用户
        PortalMemberDTO login = PortalMemberContextHolder.get();
        if (login == null) {
            AddCartUtil.notLoginAddCart(requestDTO.getNumber(), OperateEnum.UPDATE, skuData);
        } else {
            List<CartItemDO> cartItemDOS = cartCacheService.list(login.getId());
            CartItemDO cartItemDO =
                AddCartUtil.loginAddCart(requestDTO.getNumber(), OperateEnum.ADD, skuData, login, cartItemDOS);
            cartMapper.updateById(cartItemDO);
            cartCacheService.insert(login.getId(), cartItemDO);
        }
        return listCartHandler.handler(null);
    }

}

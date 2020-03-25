package com.dmall.cart.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.cart.generator.service.ICartItemService;
import org.springframework.stereotype.Service;

/**
 * @description: 购物车表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class ICartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItemDO> implements ICartItemService {

}

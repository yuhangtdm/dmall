package com.dmall.oms.generator.service.impl;

import com.dmall.oms.generator.dataobject.CartItemDO;
import com.dmall.oms.generator.mapper.CartItemMapper;
import com.dmall.oms.generator.service.ICartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 购物车表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class ICartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItemDO> implements ICartItemService {

}

package com.dmall.oms.generator.service.impl;

import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 订单项表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class IOrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemDO> implements IOrderItemService {

}

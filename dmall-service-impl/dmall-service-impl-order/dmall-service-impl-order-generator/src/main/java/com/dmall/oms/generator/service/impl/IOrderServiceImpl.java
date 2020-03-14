package com.dmall.oms.generator.service.impl;

import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 订单表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements IOrderService {

}

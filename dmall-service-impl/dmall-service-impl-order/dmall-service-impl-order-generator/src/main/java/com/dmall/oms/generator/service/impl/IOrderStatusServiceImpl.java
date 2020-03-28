package com.dmall.oms.generator.service.impl;

import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.mapper.OrderStatusMapper;
import com.dmall.oms.generator.service.IOrderStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 订单状态记录表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class IOrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatusDO> implements IOrderStatusService {

}

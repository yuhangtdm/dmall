package com.dmall.oms.generator.service.impl;

import com.dmall.oms.generator.dataobject.OrderOperateLogDO;
import com.dmall.oms.generator.mapper.OrderOperateLogMapper;
import com.dmall.oms.generator.service.IOrderOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 订单状态记录表
 * @author: created by hang.yu on 2020-03-11 22:46:57
 */
@Service
public class IOrderOperateLogServiceImpl extends ServiceImpl<OrderOperateLogMapper, OrderOperateLogDO> implements IOrderOperateLogService {

}

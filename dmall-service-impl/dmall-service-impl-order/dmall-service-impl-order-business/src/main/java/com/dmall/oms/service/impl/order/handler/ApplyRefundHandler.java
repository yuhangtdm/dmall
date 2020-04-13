package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 申请退款处理器
 * @author: created by hang.yu on 2020/4/13 22:11
 */
@Component
public class ApplyRefundHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO,Long > {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<Long> processor(Long orderItemId) {
        // 校验orderItem存在
        OrderItemDO orderItemDO = orderItemMapper.selectById(orderItemId);
        if (orderItemDO == null){
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 校验order存在
        OrderDO orderDO = orderMapper.selectById(orderItemDO.getOrderId());
        if (orderDO == null){
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 校验订单状态必须为 待发货
        if (!OrderStatusEnum.WAIT_SHIP.getCode().equals(orderDO.getStatus())){
            return ResultUtil.fail(OrderErrorEnum.APPLY_REFUND_ERROR);
        }
        // 根据订单支付类型调用具体的退款结果

        // 释放库存
        // 修改订单状态为交易关闭 不可分单、发货、收货、评价
        // 如果已分单 则修改子订单状态
        // 同步到es
        return null;
    }
}

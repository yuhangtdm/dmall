package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 取消订单处理器
 * @author: created by hang.yu on 2020/4/4 14:57
 */
@Component
public class CancelOrderHandler extends AbstractCommonHandler<Long, OrderDO, Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null){
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        if (!OrderStatusEnum.WAIT_PAY.getCode().equals(orderDO.getStatus())){
            return ResultUtil.fail(OrderErrorEnum.CANCEL_STATUS_ERROR);
        }
        orderDO.setStatus(OrderStatusEnum.CANCELED.getCode());
        orderDO.setCancelTime(new Date());
        orderDO.setCancelType(CancelTypeEnum.HANDLE.getCode());
        orderMapper.updateById(orderDO);
        return ResultUtil.success(orderId);
    }
}

package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除订单处理器
 * @author: created by hang.yu on 2020/4/4 14:57
 */
@Component
public class DeleteOrderHandler extends AbstractCommonHandler<Long, OrderDO, Long> {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null){
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        if (!OrderStatusEnum.CANCELED.getCode().equals(orderDO.getStatus()) ||
                !OrderStatusEnum.COMPLETED.getCode().equals(orderDO.getStatus())){
            return ResultUtil.fail(OrderErrorEnum.DELETE_STATUS_ERROR);
        }
        // todo 订单在退款中  不可删除
        orderMapper.deleteById(orderId);
        return ResultUtil.success(orderId);
    }
}

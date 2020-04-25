package com.dmall.oms.service.impl.order.handler.buyer;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.support.OrderLogSupport;
import com.dmall.oms.service.support.OrderStatusSupport;
import com.dmall.oms.service.support.SyncEsOrderSupport;
import com.dmall.oms.service.validate.OmsValidate;
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

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = omsValidate.validateOrder(orderId);
        omsValidate.authentication(orderDO.getCreator());
        if (!OrderStatusEnum.WAIT_PAY.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.CANCEL_STATUS_ERROR);
        }
        orderDO.setStatus(OrderStatusEnum.CANCELED.getCode());
        orderDO.setCancelTime(new Date());
        orderDO.setCancelType(CancelTypeEnum.HANDLE.getCode());
        orderMapper.updateById(orderDO);
        orderStatusSupport.insert(orderId, OrderStatusEnum.CANCELED.getCode());
        orderLogSupport.insert(orderId, OrderOperateEnum.CANCEL, false);
        syncEsOrderSupport.sendOrderEsMq(orderId);
        // 调用支付宝的关闭交易接口
        return ResultUtil.success(orderId);
    }
}

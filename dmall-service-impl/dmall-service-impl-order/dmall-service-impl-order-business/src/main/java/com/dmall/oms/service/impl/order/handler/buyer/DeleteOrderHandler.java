package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.support.AfterSaleSupport;
import com.dmall.oms.service.impl.support.OrderLogSupport;
import com.dmall.oms.service.impl.support.SyncEsOrderSupport;
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

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private AfterSaleSupport afterSaleSupport;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        if (!OrderStatusEnum.CANCELED.getCode().equals(orderDO.getStatus()) ||
                !OrderStatusEnum.COMPLETED.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OrderErrorEnum.DELETE_STATUS_ERROR);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderDO.getCreator())){
            return ResultUtil.fail(OrderErrorEnum.NO_AUTHORITY);
        }

        if (CollUtil.isNotEmpty(afterSaleSupport.listInNotDeleteStatus(orderId))){
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_ING);
        }

        orderMapper.deleteById(orderId);
        orderLogSupport.insert(orderId, OrderOperateEnum.DELETE, false);
        syncEsOrderSupport.sendOrderEsMq(orderId);
        return ResultUtil.success(orderId);
    }
}

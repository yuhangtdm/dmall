package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.api.enums.OrderOperateEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.support.AfterSaleSupport;
import com.dmall.oms.service.support.OrderLogSupport;
import com.dmall.oms.service.support.SyncEsOrderSupport;
import com.dmall.oms.service.validate.OmsValidate;
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

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<Long> processor(Long orderId) {
        OrderDO orderDO = omsValidate.validateOrder(orderId);
        omsValidate.authentication(orderDO.getCreator());
        if (!OrderStatusEnum.CANCELED.getCode().equals(orderDO.getStatus()) ||
                !OrderStatusEnum.COMPLETED.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.DELETE_STATUS_ERROR);
        }

        if (CollUtil.isNotEmpty(afterSaleSupport.listInNotDeleteStatus(orderId))) {
            return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_ING);
        }
        orderMapper.deleteById(orderId);
        orderLogSupport.insert(orderId, OrderOperateEnum.DELETE, false);
        syncEsOrderSupport.sendOrderEsMq(orderId);
        return ResultUtil.success(orderId);
    }
}

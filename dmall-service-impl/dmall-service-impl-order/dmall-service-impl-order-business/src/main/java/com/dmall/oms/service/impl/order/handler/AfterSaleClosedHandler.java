package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 关闭售后单处理器
 * @author: created by hang.yu on 2020/4/15 23:19
 */
@Component
public class AfterSaleClosedHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, Long> {

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Override
    public BaseResult processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(afterSaleId);
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        if (AfterSaleTypeEnum.REFUND.getCode().equals(orderAfterSaleApplyDO.getType())) {
            // 非 退款中不可关闭
            if (!AfterSaleStatusEnum.REFUND_PROGRESS.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_CLOSED);
            }
        } else {
            // 非 已申请和待寄回不可关闭
            if (!AfterSaleStatusEnum.APPLY.getCode().equals(orderAfterSaleApplyDO.getStatus()) &&
                    !AfterSaleStatusEnum.WAIT_SEND_BACK.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_CLOSED);
            }
        }
        orderAfterSaleApplyDO.setCloseTime(new Date());
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.CLOSED.getCode());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        return ResultUtil.success(afterSaleId);
    }
}

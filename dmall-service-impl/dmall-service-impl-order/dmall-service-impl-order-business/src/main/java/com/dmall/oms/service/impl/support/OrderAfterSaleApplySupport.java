package com.dmall.oms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: OrderAfterSaleApplySupport
 * @author: created by hang.yu on 2020/4/16 23:32
 */
@Component
public class OrderAfterSaleApplySupport {

    private static final List<Integer> STATUS = Lists.newArrayList(AfterSaleStatusEnum.APPLY.getCode(),
            AfterSaleStatusEnum.WAIT_SEND_BACK.getCode(), AfterSaleStatusEnum.RE_PROGRESS.getCode(),
            AfterSaleStatusEnum.REFUND_PROGRESS.getCode());

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    public List<OrderAfterSaleApplyDO> listByOrderId(Long orderId) {
        return orderAfterSaleApplyMapper.selectList(Wrappers.<OrderAfterSaleApplyDO>lambdaQuery()
                .eq(OrderAfterSaleApplyDO::getOrderId, orderId)
                .in(OrderAfterSaleApplyDO::getStatus, STATUS));
    }
}

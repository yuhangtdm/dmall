package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.writelogisticsno.WriteLogisticsNoRequestDTO;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 填写物流单号
 * @author: created by hang.yu on 2020/4/15 22:47
 */
@Component
public class WriteLogisticsNoHandler extends AbstractCommonHandler<WriteLogisticsNoRequestDTO, OrderAfterSaleApplyDO, Long> {

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Override
    public BaseResult<Long> processor(WriteLogisticsNoRequestDTO requestDTO) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(requestDTO.getAfterSaleId());
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        if (AfterSaleStatusEnum.WAIT_SEND_BACK.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_APPROVAL);
        }
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.RE_PROGRESS.getCode());
        orderAfterSaleApplyDO.setWriteLogisticsNoTime(new Date());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        return ResultUtil.success(orderAfterSaleApplyDO.getId());
    }
}

package com.dmall.oms.service.impl.order.handler.aftersale;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.writelogisticsno.WriteLogisticsNoRequestDTO;
import com.dmall.oms.api.enums.AfterSaleLogTitleEnum;
import com.dmall.oms.api.enums.AfterSaleLogTypeEnum;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.service.impl.support.OrderAfterSaleLogSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 填写物流单号
 * @author: created by hang.yu on 2020/4/15 22:47
 */
@Component
public class WriteLogisticsNoHandler extends AbstractCommonHandler<WriteLogisticsNoRequestDTO, OrderAfterSaleApplyDO, Long> {

    private static final String LOG_CONTENT = "填写了物流单号:{}";

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Override
    public BaseResult<Long> processor(WriteLogisticsNoRequestDTO requestDTO) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(requestDTO.getAfterSaleId());
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderAfterSaleApplyDO.getCreator())){
            return ResultUtil.fail(OrderErrorEnum.NO_AUTHORITY);
        }
        if (AfterSaleStatusEnum.WAIT_SEND_BACK.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_APPROVAL);
        }
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.RE_PROGRESS.getCode());
        orderAfterSaleApplyDO.setWriteLogisticsNoTime(new Date());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.MEMBER,
                AfterSaleLogTitleEnum.WRITE_LOGISTICS_NO, StrUtil.format(LOG_CONTENT, requestDTO.getLogisticsNo()));
        return ResultUtil.success(orderAfterSaleApplyDO.getId());
    }
}

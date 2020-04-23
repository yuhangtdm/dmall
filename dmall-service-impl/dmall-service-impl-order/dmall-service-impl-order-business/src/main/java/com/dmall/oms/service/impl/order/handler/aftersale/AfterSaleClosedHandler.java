package com.dmall.oms.service.impl.order.handler.aftersale;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.service.impl.support.OrderAfterSaleLogSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 关闭售后单处理器
 * @author: created by hang.yu on 2020/4/15 23:19
 */
@Component
public class AfterSaleClosedHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, Long> {

    private static final String LOG_CONTENT = "您的服务单{}已关闭";

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Override
    public BaseResult processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(afterSaleId);
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderAfterSaleApplyDO.getCreator())){
            return ResultUtil.fail(OmsErrorEnum.NO_AUTHORITY);
        }
        if (AfterSaleTypeEnum.REFUND.getCode().equals(orderAfterSaleApplyDO.getType())) {
            // 非 退款中不可关闭
            if (!AfterSaleStatusEnum.REFUND_PROGRESS.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_CLOSED);
            }
        } else {
            // 非 已申请和待寄回不可关闭
            if (!AfterSaleStatusEnum.APPLY.getCode().equals(orderAfterSaleApplyDO.getStatus()) &&
                    !AfterSaleStatusEnum.WAIT_SEND_BACK.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
                return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_CLOSED);
            }
        }
        orderAfterSaleApplyDO.setCloseTime(new Date());
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.CLOSED.getCode());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.MEMBER,
                AfterSaleLogTitleEnum.CLOSED, StrUtil.format(LOG_CONTENT, orderAfterSaleApplyDO.getId()));
        return ResultUtil.success(afterSaleId);
    }
}

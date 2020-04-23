package com.dmall.oms.service.impl.order.handler.aftersale;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.AfterSaleLogTitleEnum;
import com.dmall.oms.api.enums.AfterSaleLogTypeEnum;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.service.impl.support.OrderAfterSaleLogSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 删除售后单处理器
 * @author: created by hang.yu on 2020/4/15 23:31
 */
@Component
public class AfterSaleDeleteHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, Long> {

    private static final String LOG_CONTENT = "服务单{}已删除";


    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Override
    public BaseResult<Long> processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(afterSaleId);
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderAfterSaleApplyDO.getCreator())){
            return ResultUtil.fail(OmsErrorEnum.NO_AUTHORITY);
        }
        // 非 已完成 和 已关闭状态 和 已拒绝不可删除
        if (!AfterSaleStatusEnum.COMPLETED.getCode().equals(orderAfterSaleApplyDO.getStatus()) &&
                !AfterSaleStatusEnum.CLOSED.getCode().equals(orderAfterSaleApplyDO.getStatus()) &&
                !AfterSaleStatusEnum.REFUSE.getCode().equals(orderAfterSaleApplyDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.AFTER_SALE_DELETE);
        }
        orderAfterSaleApplyDO.setStatus(AfterSaleStatusEnum.DELETED.getCode());
        orderAfterSaleApplyDO.setDeleteTime(new Date());
        orderAfterSaleApplyMapper.updateById(orderAfterSaleApplyDO);
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(orderAfterSaleApplyDO.getId(), AfterSaleLogTypeEnum.MEMBER,
                AfterSaleLogTitleEnum.DELETED, StrUtil.format(LOG_CONTENT, orderAfterSaleApplyDO.getId()));
        return ResultUtil.success(afterSaleId);
    }
}

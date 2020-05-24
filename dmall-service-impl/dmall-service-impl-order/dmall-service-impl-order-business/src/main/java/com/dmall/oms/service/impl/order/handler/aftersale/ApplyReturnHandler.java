package com.dmall.oms.service.impl.order.handler.aftersale;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.applyreturn.OrderApplyReturnRequestDTO;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.service.support.OrderAfterSaleLogSupport;
import com.dmall.oms.service.validate.OmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 退货申请处理器
 * @author: created by hang.yu on 2020/4/14 22:59
 */
@Component
public class ApplyReturnHandler extends AbstractCommonHandler<OrderApplyReturnRequestDTO, OrderAfterSaleApplyDO, Long> {

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderAfterSaleLogSupport orderAfterSaleLogSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult processor(OrderApplyReturnRequestDTO requestDTO) {
        // 校验orderItem存在
        OrderItemDO orderItemDO = omsValidate.validateOrderItem(requestDTO.getOrderItemId());
        // 校验order存在
        OrderDO orderDO = omsValidate.validateOrder(orderItemDO.getOrderId());
        // 校验订单状态必须为 已完成
        if (OrderStatusEnum.COMPLETED.getCode().equals(orderDO.getStatus())) {
            return ResultUtil.fail(OmsErrorEnum.APPLY_REFUND_ERROR);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderDO.getCreator())) {
            return ResultUtil.fail(OmsErrorEnum.NO_AUTHORITY);
        }
        // 新增售后服务表
        Long id = insertAfterSale(requestDTO, orderItemDO, orderDO);
        // 新增售后日志记录
        orderAfterSaleLogSupport.insertAfterSaleLog(id, AfterSaleLogTypeEnum.MEMBER,
                AfterSaleLogTitleEnum.APPLY_RETURN, requestDTO.getReason());
        return ResultUtil.success(id);
    }

    /**
     * 新增售后表
     */
    private Long insertAfterSale(OrderApplyReturnRequestDTO requestDTO, OrderItemDO orderItemDO, OrderDO orderDO) {
        OrderAfterSaleApplyDO entity = new OrderAfterSaleApplyDO();
        entity.setOrderId(orderDO.getId());
        entity.setSubOrderId(orderItemDO.getSubOrderId());
        entity.setOrderItemId(orderItemDO.getId());
        entity.setSkuId(orderItemDO.getSkuId());
        entity.setType(AfterSaleTypeEnum.RETURN.getCode());
        entity.setStatus(AfterSaleStatusEnum.APPLY.getCode());
        entity.setReason(requestDTO.getReason());
        entity.setDescription(requestDTO.getDescription());
        entity.setApplyCredentials(requestDTO.getApplyCredentials());
        entity.setRefundType(1);
        entity.setBuyerName(requestDTO.getBuyerName());
        entity.setBuyerPhone(requestDTO.getBuyerPhone());
        entity.setBuyerDetailAddress(requestDTO.getBuyerDetailAddress());
        entity.setApplyTime(new Date());
        orderAfterSaleApplyMapper.insert(entity);
        return entity.getId();
    }
}

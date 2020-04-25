package com.dmall.oms.service.validate;

import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.oms.api.enums.OmsErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 订单公共校验服务
 * @author: created by hang.yu on 2020/4/25 9:29
 */
@Component
public class OmsValidate {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    /**
     * 校验订单必须存在
     */
    public OrderDO validateOrder(Long orderId) {
        // 校验order存在
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            throw new BusinessException(OmsErrorEnum.ORDER_NOT_EXISTS);
        }
        return orderDO;
    }

    /**
     * 校验订单项必须存在
     */
    public OrderItemDO validateOrderItem(Long orderItemId) {
        OrderItemDO orderItemDO = orderItemMapper.selectById(orderItemId);
        if (orderItemDO == null) {
            throw new BusinessException(OmsErrorEnum.ORDER_ITEM_NOT_EXISTS);
        }
        return orderItemDO;
    }

    /**
     * 校验子订单必须存在
     */
    public SubOrderDO validateSubOrder(Long subOrderId){
        SubOrderDO subOrderDO = subOrderMapper.selectById(subOrderId);
        if (subOrderDO == null) {
            throw new BusinessException(OmsErrorEnum.SUB_ORDER_NOT_EXISTS);
        }
        return subOrderDO;
    }

    /**
     * 校验售后单必须存在
     */
    public OrderAfterSaleApplyDO validateOrderAfterSale(Long orderAfterSaleId){
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(orderAfterSaleId);
        if (orderAfterSaleApplyDO == null) {
            throw new BusinessException(OmsErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        return orderAfterSaleApplyDO;
    }

    /**
     * 横向鉴权
     */
    public void authentication(Long creator){
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(creator)){
            throw new BusinessException(OmsErrorEnum.NO_AUTHORITY);
        }
    }
}

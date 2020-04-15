package com.dmall.oms.service.impl.order.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDTO;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDetailResponseDTO;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.AfterSaleStatusEnum;
import com.dmall.oms.api.enums.AfterSaleTypeEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 售后详情处理器
 * @author: created by hang.yu on 2020/4/15 22:12
 */
@Component
public class AfterSaleDetailHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, AfterSaleDetailResponseDTO> {

    @Autowired
    private OrderAfterSaleApplyMapper orderAfterSaleApplyMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public BaseResult<AfterSaleDetailResponseDTO> processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = orderAfterSaleApplyMapper.selectById(afterSaleId);
        if (orderAfterSaleApplyDO == null) {
            return ResultUtil.fail(OrderErrorEnum.AFTER_SALE_NOT_EXISTS);
        }
        OrderItemDO orderItemDO = orderItemMapper.selectById(orderAfterSaleApplyDO.getOrderItemId());
        if (orderItemDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        return ResultUtil.success(buildAfterSaleDetailResponse(orderAfterSaleApplyDO, orderItemDO));
    }

    private AfterSaleDetailResponseDTO buildAfterSaleDetailResponse(OrderAfterSaleApplyDO orderAfterSaleApplyDO,
                                                                    OrderItemDO orderItemDO) {
        AfterSaleDetailResponseDTO afterSaleDetailResponseDTO = new AfterSaleDetailResponseDTO();
        AfterSaleDTO afterSaleDTO = BeanUtil.copyProperties(orderAfterSaleApplyDO, AfterSaleDTO.class);
        afterSaleDTO.setAfterSaleId(orderAfterSaleApplyDO.getId());
        afterSaleDTO.setType(EnumUtil.getCodeDescEnum(AfterSaleTypeEnum.class, orderAfterSaleApplyDO.getType()));
        afterSaleDTO.setStatus(EnumUtil.getCodeDescEnum(AfterSaleStatusEnum.class, orderAfterSaleApplyDO.getStatus()));
        afterSaleDetailResponseDTO.setAfterSaleDTO(afterSaleDTO);
        BuyerOrderItemDTO buyerOrderItemDTO = new BuyerOrderItemDTO();
        buyerOrderItemDTO.setOrderItemId(orderItemDO.getId());
        buyerOrderItemDTO.setSkuId(orderItemDO.getSkuId());
        buyerOrderItemDTO.setSkuName(orderItemDO.getSkuName());
        buyerOrderItemDTO.setSkuPrice(orderItemDO.getSkuPrice());
        buyerOrderItemDTO.setSkuNumber(orderItemDO.getSkuNumber());
        buyerOrderItemDTO.setSkuMainPic(orderItemDO.getSkuPic());
        buyerOrderItemDTO.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
        afterSaleDetailResponseDTO.setBuyerOrderItemDTO(buyerOrderItemDTO);
        return afterSaleDetailResponseDTO;
    }
}

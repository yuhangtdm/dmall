package com.dmall.oms.service.impl.order.handler.aftersale;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.aftersaledetail.AfterSaleDetailResponseDTO;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.generator.dataobject.OrderAfterSaleApplyDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderAfterSaleApplyMapper;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.service.support.AfterSaleSupport;
import com.dmall.oms.service.validate.OmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 售后详情处理器
 * @author: created by hang.yu on 2020/4/15 22:12
 */
@Component
public class AfterSaleDetailHandler extends AbstractCommonHandler<Long, OrderAfterSaleApplyDO, AfterSaleDetailResponseDTO> {

    @Autowired
    private AfterSaleSupport afterSaleSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<AfterSaleDetailResponseDTO> processor(Long afterSaleId) {
        OrderAfterSaleApplyDO orderAfterSaleApplyDO = omsValidate.validateOrderAfterSale(afterSaleId);
        OrderItemDO orderItemDO = omsValidate.validateOrderItem(orderAfterSaleApplyDO.getOrderItemId());
        return ResultUtil.success(buildAfterSaleDetailResponse(afterSaleId, orderItemDO));
    }

    private AfterSaleDetailResponseDTO buildAfterSaleDetailResponse(Long afterSaleId, OrderItemDO orderItemDO) {
        AfterSaleDetailResponseDTO afterSaleDetailResponseDTO = new AfterSaleDetailResponseDTO();
        BuyerOrderItemDTO buyerOrderItemDTO = new BuyerOrderItemDTO();
        buyerOrderItemDTO.setOrderItemId(orderItemDO.getId());
        buyerOrderItemDTO.setSkuId(orderItemDO.getSkuId());
        buyerOrderItemDTO.setSkuName(orderItemDO.getSkuName());
        buyerOrderItemDTO.setSkuPrice(orderItemDO.getSkuPrice());
        buyerOrderItemDTO.setSkuNumber(orderItemDO.getSkuNumber());
        buyerOrderItemDTO.setSkuMainPic(orderItemDO.getSkuPic());
        buyerOrderItemDTO.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
        afterSaleDetailResponseDTO.setBuyerOrderItem(buyerOrderItemDTO);
        afterSaleDetailResponseDTO.setAfterSale(afterSaleSupport.buildAfterSale(afterSaleId));
        return afterSaleDetailResponseDTO;
    }
}

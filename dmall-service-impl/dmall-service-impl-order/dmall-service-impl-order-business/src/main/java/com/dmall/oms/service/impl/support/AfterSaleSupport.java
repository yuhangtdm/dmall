package com.dmall.oms.service.impl.support;

import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/15 23:12
 */
public class AfterSaleSupport {

    /**
     * 构建申请退款实体
     */
    public static ApplyRefundRequestDTO buildApplyRefundRequest(OrderItemDO orderItemDO, Integer paymentType, String reason) {
        ApplyRefundRequestDTO applyRefundRequestDTO = new ApplyRefundRequestDTO();
        applyRefundRequestDTO.setOrderId(orderItemDO.getOrderId());
        applyRefundRequestDTO.setOrderItemId(orderItemDO.getId());
        applyRefundRequestDTO.setSubOrderId(orderItemDO.getSubOrderId());
        applyRefundRequestDTO.setAmount(orderItemDO.getRealPrice());
        applyRefundRequestDTO.setPaymentType(paymentType);
        applyRefundRequestDTO.setRefundReason(reason);
        return applyRefundRequestDTO;
    }

    /**
     * 构建释放库存请求实体
     */
    public static StockRequestDTO buildStockRequest(OrderItemDO orderItemDO) {
        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        List<SkuStockRequestDTO> skuList = Lists.newArrayList(new SkuStockRequestDTO()
                .setSkuId(orderItemDO.getSkuId()).setNumber(orderItemDO.getSkuNumber()));
        stockRequestDTO.setSku(skuList);
        return stockRequestDTO;
    }
}

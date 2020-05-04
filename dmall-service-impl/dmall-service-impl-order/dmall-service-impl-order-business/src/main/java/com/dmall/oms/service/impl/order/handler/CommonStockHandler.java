package com.dmall.oms.service.impl.order.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: CommonStockHandler
 * @author: created by hang.yu on 2020/4/4 14:48
 */
@Component
public class CommonStockHandler {

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 构建库存实体
     */
    public StockRequestDTO build(Long orderId) {
        List<OrderItemDO> orderItemList = orderItemMapper.selectList(Wrappers.<OrderItemDO>lambdaQuery()
                .eq(OrderItemDO::getOrderId, orderId));
        List<SkuStockRequestDTO> skuList = orderItemList.stream().map(orderItemDO -> {
            SkuStockRequestDTO skuStockRequestDTO = new SkuStockRequestDTO();
            skuStockRequestDTO.setSkuId(orderItemDO.getSkuId());
            skuStockRequestDTO.setNumber(orderItemDO.getSkuNumber());
            return skuStockRequestDTO;
        }).collect(Collectors.toList());
        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        stockRequestDTO.setSku(skuList);
        return stockRequestDTO;
    }

}

package com.dmall.oms.service.impl.order.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.items.OrderItemListResponseDTO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 查询订单明细处理器
 * @author: created by hang.yu on 2020/4/5 10:15
 */
@Component
public class OrderItemListHandler extends AbstractCommonHandler<Long, OrderItemDO, OrderItemListResponseDTO> {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public BaseResult<List<OrderItemListResponseDTO>> processor(Long orderId) {
        List<OrderItemDO> orderItemList = orderItemMapper.selectList(Wrappers.<OrderItemDO>lambdaQuery()
                .eq(OrderItemDO::getOrderId, orderId));
        List<OrderItemListResponseDTO> collect = orderItemList.stream()
                .map(orderItemDO -> doConvertDto(orderItemDO, OrderItemListResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}

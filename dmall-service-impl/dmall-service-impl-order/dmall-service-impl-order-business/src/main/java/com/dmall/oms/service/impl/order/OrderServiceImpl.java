package com.dmall.oms.service.impl.order;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.listBackground.PageOrderRequestDTO;
import com.dmall.oms.api.dto.listBackground.PageOrderResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.api.service.OrderService;
import com.dmall.oms.service.impl.order.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 订单服务
 * @author: created by hang.yu on 2020/3/26 23:02
 */
@RestController
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ToTradeHandler toTradeHandler;

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private CancelOrderHandler cancelOrderHandler;

    @Autowired
    private DeleteOrderHandler deleteOrderHandler;

    @Autowired
    private DemolitionOrderPageHandler demolitionOrderPageHandler;

    @Override
    public BaseResult<ToTradeResponseDTO> toTrade(@RequestBody ToTradeRequestDTO requestDTO) {
        return toTradeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> createOrder(@RequestBody CreateOrderRequestDTO requestDTO) {
        return createOrderHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> cancelOrder(Long orderId) {
        return cancelOrderHandler.handler(orderId);
    }

    @Override
    public BaseResult<Long> deleteOrder(Long orderId) {
        return deleteOrderHandler.handler(orderId);
    }

    @Override
    public BaseResult<ResponsePage<PageOrderResponseDTO>> demolitionOrderPage(@RequestBody PageOrderRequestDTO requestDTO) {
        return demolitionOrderPageHandler.handler(requestDTO);
    }


}

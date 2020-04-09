package com.dmall.oms.service.impl.order;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.buyerdetail.BuyerOrderDetailResponseDTO;
import com.dmall.oms.api.dto.buyerorderpage.BuyerOrderPageRequestDTO;
import com.dmall.oms.api.dto.buyerorderpage.response.BuyerOrderPageResponseDTO;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.deliver.DeliverRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageResponseDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageResponseDTO;
import com.dmall.oms.api.dto.items.OrderItemListResponseDTO;
import com.dmall.oms.api.dto.sellerdetail.SellerOrderDetailResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import com.dmall.oms.api.service.OrderService;
import com.dmall.oms.service.impl.order.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private OrderItemListHandler orderItemListHandler;

    @Autowired
    private DemolitionOrderHandler demolitionOrderHandler;

    @Autowired
    private DeliverOrderPageHandler deliverOrderPageHandler;

    @Autowired
    private DeliverHandler deliverHandler;

    @Autowired
    private BuyerOrderDetailHandler buyerOrderDetailHandler;

    @Autowired
    private SellerOrderDetailHandler sellerOrderDetailHandler;

    @Autowired
    private BuyerOrderPageHandler buyerOrderPageHandler;

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
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> cancelOrder(Long orderId) {
        return cancelOrderHandler.handler(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> deleteOrder(Long orderId) {
        return deleteOrderHandler.handler(orderId);
    }

    @Override
    public BaseResult<ResponsePage<DemolitionOrderPageResponseDTO>> demolitionOrderPage(@RequestBody DemolitionOrderPageRequestDTO requestDTO) {
        return demolitionOrderPageHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> demolitionOrder(@RequestBody DemolitionOrderRequestDTO requestDTO) {
        return demolitionOrderHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<OrderItemListResponseDTO>> items(Long orderId) {
        return orderItemListHandler.handler(orderId);
    }

    @Override
    public BaseResult<ResponsePage<DeliverOrderPageResponseDTO>> deliverOrderPage(@RequestBody DeliverOrderPageRequestDTO requestDTO) {
        return deliverOrderPageHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> deliver(@RequestBody DeliverRequestDTO requestDTO) {
        return deliverHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<BuyerOrderDetailResponseDTO> buyerDetail(Long subOrderId) {
        return buyerOrderDetailHandler.handler(subOrderId);
    }

    @Override
    public BaseResult<SellerOrderDetailResponseDTO> sellerDetail(Long orderId) {
        return sellerOrderDetailHandler.handler(orderId);
    }

    @Override
    public BaseResult<ResponsePage<BuyerOrderPageResponseDTO>> sellerOrderPage(@RequestBody BuyerOrderPageRequestDTO requestDTO) {
        return buyerOrderPageHandler.handler(requestDTO);
    }

}

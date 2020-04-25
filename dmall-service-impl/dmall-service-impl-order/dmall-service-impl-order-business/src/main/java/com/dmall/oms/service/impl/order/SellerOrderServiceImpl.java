package com.dmall.oms.service.impl.order;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.deliver.DeliverRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageResponseDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageResponseDTO;
import com.dmall.oms.api.dto.items.OrderItemListResponseDTO;
import com.dmall.oms.api.dto.sellerdetail.SellerOrderDetailResponseDTO;
import com.dmall.oms.api.service.SellerOrderService;
import com.dmall.oms.service.impl.order.handler.aftersale.OrderItemListHandler;
import com.dmall.oms.service.impl.order.handler.seller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 卖家端订单服务实现
 * @author: created by hang.yu on 2020/4/18 12:44
 */
@RestController
public class SellerOrderServiceImpl implements SellerOrderService {

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
    private SellerOrderDetailHandler sellerOrderDetailHandler;

    @Autowired
    private SkuSaleCountHandler skuSaleCountHandler;

    @Autowired
    private ProductSaleCountHandler productSaleCountHandler;

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
    public BaseResult<SellerOrderDetailResponseDTO> sellerDetail(Long orderId) {
        return sellerOrderDetailHandler.handler(orderId);
    }

    @Override
    public BaseResult<Integer> skuSaleCount(Long skuId) {
        return skuSaleCountHandler.handler(skuId);
    }

    @Override
    public BaseResult<Integer> productSaleCount(Long productId) {
        return productSaleCountHandler.handler(productId);
    }

}

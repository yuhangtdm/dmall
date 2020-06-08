package com.dmall.oms.api.service;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 卖家端订单服务
 * @author: created by hang.yu on 2020/4/18 12:37
 */
@Api(tags = "卖家端订单服务")
@RequestMapping("/seller/order")
public interface SellerOrderService {

    @GetMapping("/demolitionOrderPage")
    @ApiOperation(value = "拆单分页")
    BaseResult<ResponsePage<DemolitionOrderPageResponseDTO>>
        demolitionOrderPage(@RequestBody @Valid DemolitionOrderPageRequestDTO requestDTO);

    @GetMapping("/demolitionOrder")
    @ApiOperation(value = "拆单")
    BaseResult<Long> demolitionOrder(@RequestBody @Valid DemolitionOrderRequestDTO requestDTO);

    @GetMapping("/items/{orderId}")
    @ApiOperation(value = "根据订单号查询订单明细")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<List<OrderItemListResponseDTO>> items(@PathVariable("orderId") Long orderId);

    @PostMapping("/deliverOrderPage")
    @ApiOperation(value = "待发货分页")
    BaseResult<ResponsePage<DeliverOrderPageResponseDTO>>
        deliverOrderPage(@RequestBody @Valid DeliverOrderPageRequestDTO requestDTO);

    @PostMapping("/deliver")
    @ApiOperation(value = "发货")
    BaseResult<Long> deliver(@RequestBody @Valid DeliverRequestDTO requestDTO);

    @GetMapping("/seller/detail/{orderId}")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "卖家端订单详情")
    BaseResult<SellerOrderDetailResponseDTO> sellerDetail(@PathVariable("orderId") Long orderId);

    @GetMapping("/skuSaleCount/{skuId}")
    @ApiImplicitParam(name = "orderId", value = "skuId", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "获取sku销量")
    BaseResult<Integer> skuSaleCount(@PathVariable("skuId") Long skuId);

    @GetMapping("/productSaleCount/{productId}")
    @ApiImplicitParam(name = "orderId", value = "productId", required = true, dataType = "int", paramType = "path")
    @ApiOperation(value = "获取商品销量")
    BaseResult<Integer> productSaleCount(@PathVariable("productId") Long productId);
}

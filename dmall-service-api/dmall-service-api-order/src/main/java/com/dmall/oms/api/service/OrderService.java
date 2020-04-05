package com.dmall.oms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.deliver.DeliverOrderPageRequestDTO;
import com.dmall.oms.api.dto.deliver.DeliverOrderPageResponseDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageRequestDTO;
import com.dmall.oms.api.dto.demolitionorderpage.DemolitionOrderPageResponseDTO;
import com.dmall.oms.api.dto.items.OrderItemListResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 订单服务
 * @author: created by hang.yu on 2020/3/26 22:29
 */
@Api(tags = "订单服务")
@RequestMapping("/order")
public interface OrderService {

    @PostMapping("/toTrade")
    @ApiOperation(value = "跳转结算页")
    BaseResult<ToTradeResponseDTO> toTrade(@RequestBody @Valid ToTradeRequestDTO requestDTO);

    @PostMapping("/createOrder")
    @ApiOperation(value = "创建订单")
    BaseResult<String> createOrder(@RequestBody @Valid CreateOrderRequestDTO requestDTO);

    @GetMapping("/cancel/{orderId}")
    @ApiOperation(value = "取消订单")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> cancelOrder(@PathVariable("orderId") Long orderId);

    @DeleteMapping("/delete/{orderId}")
    @ApiOperation(value = "删除订单")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> deleteOrder(@PathVariable("orderId") Long orderId);

    @GetMapping("/demolitionOrderPage")
    @ApiOperation(value = "拆单分页")
    BaseResult<ResponsePage<DemolitionOrderPageResponseDTO>> demolitionOrderPage(@RequestBody @Valid DemolitionOrderPageRequestDTO requestDTO);

    @GetMapping("/demolitionOrder")
    @ApiOperation(value = "拆单")
    BaseResult<Long> demolitionOrder(@RequestBody @Valid DemolitionOrderRequestDTO requestDTO);

    @GetMapping("/items/{orderId}")
    @ApiOperation(value = "根据订单号查询订单明细")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<List<OrderItemListResponseDTO>> items(@PathVariable("orderId") Long orderId);

    @GetMapping("/deliverOrderPage")
    @ApiOperation(value = "待发货分页")
    BaseResult<ResponsePage<DeliverOrderPageResponseDTO>> deliverOrderPage(@RequestBody @Valid DeliverOrderPageRequestDTO requestDTO);
}
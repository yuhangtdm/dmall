package com.dmall.oms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.listBackground.PageOrderRequestDTO;
import com.dmall.oms.api.dto.listBackground.PageOrderResponseDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/delete/{orderId}")
    @ApiOperation(value = "删除订单")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> deleteOrder(@PathVariable("orderId") Long orderId);

    @GetMapping("/demolitionOrderPage")
    @ApiOperation(value = "拆单分页")
    BaseResult<ResponsePage<PageOrderResponseDTO>> demolitionOrderPage(@RequestBody @Valid PageOrderRequestDTO requestDTO);

}

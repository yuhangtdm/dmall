package com.dmall.oms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.totrade.request.ToTradeRequestDTO;
import com.dmall.oms.api.dto.totrade.response.ToTradeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @ApiOperation(value = "跳转结算页")
    BaseResult<String> createOrder(@RequestBody @Valid CreateOrderRequestDTO requestDTO);
}

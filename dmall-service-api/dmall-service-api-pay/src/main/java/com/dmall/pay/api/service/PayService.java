package com.dmall.pay.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import com.dmall.pay.api.dto.PaymentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 支付服务
 * @author: created by hang.yu on 2020/4/2 22:37
 */
@Api(tags = "支付服务")
@RequestMapping("/pay")
public interface PayService {

    @PostMapping("/createPayment")
    @ApiOperation(value = "创建支付单")
    BaseResult<PayResponseDTO> createPayment(@RequestBody @Valid PayRequestDTO requestDTO);

    @GetMapping("/alipay/callback/return")
    @ApiOperation(value = "支付宝支付回调")
    BaseResult<PayResponseDTO> callback();

    @GetMapping("/listByOrderId/{orderId}")
    @ApiOperation(value = "根据订单号查询支付记录")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<List<PaymentResponseDTO>> listByOrderId(@PathVariable("orderId") Long orderId);
}

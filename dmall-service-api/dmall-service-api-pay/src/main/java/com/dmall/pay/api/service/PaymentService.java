package com.dmall.pay.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;
import com.dmall.pay.api.dto.listpayment.ListPaymentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 支付服务
 * @author: created by hang.yu on 2020/4/2 22:37
 */
@Api(tags = "支付服务")
@RequestMapping("/pay")
public interface PaymentService {

    @PostMapping("/createPayment")
    @ApiOperation(value = "创建支付单")
    BaseResult<ListPaymentResponseDTO> createPayment(@RequestBody @Valid CreatePaymentRequestDTO requestDTO);

    @GetMapping("/aliPay/callback/return")
    @ApiOperation(value = "支付宝支付回调")
    BaseResult<CreatePaymentResponseDTO> callback();

    @GetMapping("/listPayment/{orderId}")
    @ApiOperation(value = "根据订单号查询支付记录")
    @ApiImplicitParam(name = "orderId", value = "订单号", required = true, dataType = "int", paramType = "path")
    BaseResult<List<ListPaymentResponseDTO>> listPayment(@PathVariable("orderId") Long orderId);

    @GetMapping("/applyRefund")
    @ApiOperation(value = "申请退款")
    BaseResult applyRefund(@RequestBody @Valid ApplyRefundRequestDTO requestDTO);
}

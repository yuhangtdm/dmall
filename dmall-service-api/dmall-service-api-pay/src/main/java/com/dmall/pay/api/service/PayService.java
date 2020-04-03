package com.dmall.pay.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    @ApiOperation(value = "创建支付单")
    BaseResult<PayResponseDTO> callback(HttpServletRequest request);
}

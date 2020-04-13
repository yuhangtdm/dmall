package com.dmall.pay.service.impl.handler.paytype;

import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;

/**
 * @description: 三方支付接口
 * @author: created by hang.yu on 2020/4/3 22:59
 */
public interface PaymentTypeService {

    /**
     * 创建支付单
     */
    CreatePaymentResponseDTO createPayment(CreatePaymentRequestDTO requestDTO);

    /**
     * 退款
     */
    void applyRefund(ApplyRefundRequestDTO requestDTO);
}

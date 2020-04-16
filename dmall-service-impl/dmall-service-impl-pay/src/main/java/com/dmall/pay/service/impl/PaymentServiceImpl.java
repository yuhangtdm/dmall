package com.dmall.pay.service.impl;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;
import com.dmall.pay.api.dto.listpayment.ListPaymentResponseDTO;
import com.dmall.pay.api.dto.refundpage.RefundPageRequestDTO;
import com.dmall.pay.api.dto.refundpage.RefundPageResponseDTO;
import com.dmall.pay.api.service.PaymentService;
import com.dmall.pay.service.impl.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 支付服务实现
 * @author: created by hang.yu on 2020/4/2 23:27
 */
@RestController
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CreatePaymentHandler createPaymentHandler;

    @Autowired
    private AliCallBackHandler aliCallBackHandler;

    @Autowired
    private ListByOrderIdHandler listByOrderIdHandler;

    @Autowired
    private ApplyRefundHandler applyRefundHandler;

    @Autowired
    private RefundPageHandler refundPageHandler;

    @Override
    public BaseResult<ListPaymentResponseDTO> createPayment(@Valid CreatePaymentRequestDTO requestDTO) {
        return createPaymentHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CreatePaymentResponseDTO> callback() {
        return aliCallBackHandler.handler(null);
    }

    @Override
    public BaseResult<List<ListPaymentResponseDTO>> listPayment(Long orderId) {
        return listByOrderIdHandler.handler(orderId);
    }

    @Override
    public BaseResult applyRefund(ApplyRefundRequestDTO requestDTO) {
        return applyRefundHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<RefundPageResponseDTO>> refundPage(@RequestBody RefundPageRequestDTO requestDTO) {
        return refundPageHandler.handler(requestDTO);
    }
}

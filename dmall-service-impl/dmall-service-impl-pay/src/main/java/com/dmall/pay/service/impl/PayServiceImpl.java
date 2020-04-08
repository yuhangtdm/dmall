package com.dmall.pay.service.impl;
import com.dmall.common.dto.BaseResult;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import com.dmall.pay.api.dto.PaymentResponseDTO;
import com.dmall.pay.api.service.PayService;
import com.dmall.pay.service.impl.handler.AliCallBackHandler;
import com.dmall.pay.service.impl.handler.ListByOrderIdHandler;
import com.dmall.pay.service.impl.handler.PayHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 支付服务实现
 * @author: created by hang.yu on 2020/4/2 23:27
 */
@RestController
public class PayServiceImpl implements PayService {

    @Autowired
    private PayHandler payHandler;

    @Autowired
    private AliCallBackHandler aliCallBackHandler;

    @Autowired
    private ListByOrderIdHandler listByOrderIdHandler;

    @Override
    public BaseResult<PayResponseDTO> createPayment(@Valid PayRequestDTO requestDTO) {
        return payHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<PayResponseDTO> callback() {
        return aliCallBackHandler.handler(null);
    }

    @Override
    public BaseResult<List<PaymentResponseDTO>> listByOrderId(Long orderId) {
        return listByOrderIdHandler.handler(orderId);
    }
}

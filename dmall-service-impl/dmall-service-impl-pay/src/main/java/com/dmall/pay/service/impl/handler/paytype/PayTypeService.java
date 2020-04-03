package com.dmall.pay.service.impl.handler.paytype;

import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;

/**
 * @description: 支付
 * @author: created by hang.yu on 2020/4/3 22:59
 */
public interface PayTypeService {

    /**
     * 创建支付单
     */
    PayResponseDTO createPayment(PayRequestDTO requestDTO);
}

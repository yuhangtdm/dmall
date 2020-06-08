package com.dmall.pay.service.impl.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;
import com.dmall.pay.api.enums.PaymentErrorEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.service.impl.handler.paytype.PaymentTypeFactory;
import com.dmall.pay.service.impl.handler.paytype.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付处理器
 * @author: created by hang.yu on 2020/4/2 23:27
 */
@Component
public class CreatePaymentHandler
    extends AbstractCommonHandler<CreatePaymentRequestDTO, PaymentInfoDO, CreatePaymentResponseDTO> {

    @Autowired
    private PaymentTypeFactory paymentTypeFactory;

    @Override
    public BaseResult<CreatePaymentResponseDTO> processor(CreatePaymentRequestDTO requestDTO) {
        PaymentTypeEnum paymentTypeEnum = EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, requestDTO.getPaymentType());
        if (paymentTypeEnum == null) {
            return ResultUtil.fail(PaymentErrorEnum.PAYMENT_ORDER_EXISTS);
        }
        PaymentTypeService instance = paymentTypeFactory.createInstance(paymentTypeEnum);
        return ResultUtil.success(instance.createPayment(requestDTO));
    }
}

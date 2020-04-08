package com.dmall.pay.service.impl.handler;

import com.dmall.common.util.EnumUtil;
import com.dmall.pay.api.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

import com.dmall.pay.api.enums.PaymentStatusEnum;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.dto.PaymentResponseDTO;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: ListByOrderIdHandler
 * @author: created by hang.yu on 2020/4/8 21:23
 */
@Component
public class ListByOrderIdHandler extends AbstractCommonHandler<Long, PaymentInfoDO, PaymentResponseDTO> {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public BaseResult processor(Long orderId) {
        List<PaymentResponseDTO> collect = paymentInfoMapper.selectList(Wrappers.<PaymentInfoDO>lambdaQuery()
                .eq(PaymentInfoDO::getOrderId, orderId)).stream().map(paymentInfoDO -> {
            PaymentResponseDTO paymentResponse = new PaymentResponseDTO();
            paymentResponse.setPaymentId(paymentInfoDO.getId());
            paymentResponse.setOrderId(paymentInfoDO.getOrderId());
            paymentResponse.setPaymentType(EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, paymentInfoDO.getPaymentType()));
            paymentResponse.setTradeNo(paymentInfoDO.getTradeNo());
            paymentResponse.setAmount(paymentInfoDO.getAmount());
            paymentResponse.setSubject(paymentInfoDO.getSubject());
            paymentResponse.setPaymentStatus(EnumUtil.getCodeDescEnum(PaymentStatusEnum.class, paymentInfoDO.getPaymentStatus()));
            paymentResponse.setCallbackContent(paymentInfoDO.getCallbackContent());
            paymentResponse.setCallBackTime(paymentInfoDO.getCallBackTime());
            paymentResponse.setBuyerAliPayNo(paymentInfoDO.getBuyerAliPayNo());
            paymentResponse.setSellerAliPayNo(paymentInfoDO.getSellerAliPayNo());


            return paymentResponse;
        }).collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}

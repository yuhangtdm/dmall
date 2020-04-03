package com.dmall.pay.service.impl.handler.paytype;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import com.dmall.pay.api.enums.PayErrorEnum;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/3 23:00
 */
public abstract class AbstractPayTypeService implements PayTypeService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public PayResponseDTO createPayment(PayRequestDTO requestDTO) {
        PaymentInfoDO paymentInfoDO = paymentInfoMapper.selectOne(Wrappers.<PaymentInfoDO>lambdaQuery()
                .eq(PaymentInfoDO::getOrderId, requestDTO.getOrderId()));
        // 一笔订单只能有一个支付单
        if (paymentInfoDO != null){
            throw new BusinessException(PayErrorEnum.PAYMENT_ORDER_EXISTS);
        }
        PayResponseDTO payResponseDTO = pay(requestDTO);
        PaymentInfoDO paymentInfo = new PaymentInfoDO();
        paymentInfo.setOrderId(requestDTO.getOrderId());
        paymentInfo.setPaymentType(requestDTO.getPayType());
        paymentInfo.setAmount(requestDTO.getAmount());
        paymentInfoDO.setSubject(requestDTO.getSubject());
        paymentInfoDO.setPaymentStatus(PaymentStatusEnum.WAIT_PAY.getCode());
        paymentInfoMapper.insert(paymentInfo);
        return payResponseDTO;
    }

    public abstract PayResponseDTO pay(PayRequestDTO requestDTO);
}

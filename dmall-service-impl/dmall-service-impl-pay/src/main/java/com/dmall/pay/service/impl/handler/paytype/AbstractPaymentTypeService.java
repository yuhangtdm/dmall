package com.dmall.pay.service.impl.handler.paytype;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.api.enums.RefundStatusEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.dataobject.RefundRecordDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import com.dmall.pay.generator.mapper.RefundRecordMapper;
import com.dmall.pay.service.support.PaymentInfoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 支付抽象模板
 * @author: created by hang.yu on 2020/4/3 23:00
 */
public abstract class AbstractPaymentTypeService implements PaymentTypeService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private PaymentInfoSupport paymentInfoSupport;

    @Autowired
    private RefundRecordMapper refundRecordMapper;

    @Override
    public CreatePaymentResponseDTO createPayment(CreatePaymentRequestDTO requestDTO) {
        CreatePaymentResponseDTO createPaymentResponseDTO = payment(requestDTO);
        PaymentInfoDO paymentInfo = new PaymentInfoDO();
        // 先更新该订单的所有支付记录为无效
        paymentInfoMapper.update(null, Wrappers.lambdaUpdate(paymentInfo)
            .eq(PaymentInfoDO::getOrderId, requestDTO.getOrderId())
            .set(PaymentInfoDO::getStatus, YNEnum.N.getCode()));
        // 在插入支付记录
        paymentInfo.setOrderId(requestDTO.getOrderId());
        paymentInfo.setPaymentType(requestDTO.getPaymentType());
        paymentInfo.setAmount(requestDTO.getAmount());
        paymentInfo.setSubject(requestDTO.getSubject());
        paymentInfo.setPaymentStatus(PaymentStatusEnum.WAIT_PAY.getCode());
        paymentInfo.setStatus(YNEnum.Y.getCode());
        paymentInfoMapper.insert(paymentInfo);
        return createPaymentResponseDTO;
    }

    @Override
    public void applyRefund(ApplyRefundRequestDTO requestDTO) {
        RefundRecordDO refundRecordDO = new RefundRecordDO();
        PaymentInfoDO paymentInfoDO = paymentInfoSupport.getByOrderId(requestDTO.getOrderId());
        refundRecordDO.setAfterSaleId(requestDTO.getAfterSaleId());
        refundRecordDO.setPaymentId(paymentInfoDO.getId());
        refundRecordDO.setOrderId(requestDTO.getOrderId());
        refundRecordDO.setOrderItemId(requestDTO.getOrderItemId());
        refundRecordDO.setSubOrderId(requestDTO.getSubOrderId());
        refundRecordDO.setAmount(requestDTO.getAmount());
        refundRecordDO.setSkuId(requestDTO.getSkuId());
        refundRecordDO.setSkuNumber(requestDTO.getSkuNumber());
        refundRecordDO.setSkuName(requestDTO.getSkuName());
        refundRecordDO.setStatus(RefundStatusEnum.REFUND_ING.getCode());
        refundRecordMapper.insert(refundRecordDO);
        BaseResult<String> refund = refund(requestDTO, paymentInfoDO.getTradeNo(), refundRecordDO.getId());
        if (refund.getResult()) {
            refundRecordDO.setStatus(RefundStatusEnum.REFUND_SUCCESS.getCode());
        } else {
            refundRecordDO.setStatus(RefundStatusEnum.REFUND_FAIL.getCode());
        }
        refundRecordMapper.updateById(refundRecordDO);
    }

    public abstract CreatePaymentResponseDTO payment(CreatePaymentRequestDTO requestDTO);

    public abstract BaseResult<String> refund(ApplyRefundRequestDTO requestDTO, String tradeNo, Long refundRecordId);
}

package com.dmall.pay.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.YNEnum;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: PaymentInfoSupport
 * @author: created by hang.yu on 2020/4/13 23:34
 */
@Component
public class PaymentInfoSupport {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    /**
     * 根据订单号获取支付记录
     */
    public PaymentInfoDO getByOrderId(Long orderId) {
        return paymentInfoMapper.selectOne(Wrappers.lambdaQuery(new PaymentInfoDO())
                .eq(PaymentInfoDO::getOrderId, orderId)
                .eq(PaymentInfoDO::getStatus, YNEnum.Y.getCode()));
    }
}

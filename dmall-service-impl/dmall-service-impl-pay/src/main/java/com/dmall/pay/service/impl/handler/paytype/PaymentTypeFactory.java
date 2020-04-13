package com.dmall.pay.service.impl.handler.paytype;

import com.dmall.pay.api.enums.PaymentTypeEnum;
import com.dmall.pay.service.impl.handler.paytype.strategy.AliPaymentTypeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付方式工厂
 * @author: created by hang.yu on 2020/4/3 22:58
 */
@Component
public class PaymentTypeFactory {

    @Autowired
    private AliPaymentTypeStrategy aliPayTypeStrategy;

    /**
     * 获取支付策略
     */
    public PaymentTypeService createInstance(PaymentTypeEnum paymentTypeEnum) {
        switch (paymentTypeEnum) {
            case ALI_PAY:
                return aliPayTypeStrategy;
            case WX_PAY:
                // 微信支付
            case UNION_PAY:
                // 银联支付
            default:
                return aliPayTypeStrategy;
        }
    }

}

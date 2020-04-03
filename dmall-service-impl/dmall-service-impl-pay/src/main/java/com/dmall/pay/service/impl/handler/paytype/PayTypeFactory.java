package com.dmall.pay.service.impl.handler.paytype;

import com.dmall.pay.api.enums.PayTypeEnum;
import com.dmall.pay.service.impl.handler.paytype.strategy.AliPayTypeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付方式工厂
 * @author: created by hang.yu on 2020/4/3 22:58
 */
@Component
public class PayTypeFactory {

    @Autowired
    private AliPayTypeStrategy aliPayTypeStrategy;

    /**
     * 获取支付策略
     */
    public PayTypeService createInstance(PayTypeEnum payTypeEnum) {
        switch (payTypeEnum) {
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

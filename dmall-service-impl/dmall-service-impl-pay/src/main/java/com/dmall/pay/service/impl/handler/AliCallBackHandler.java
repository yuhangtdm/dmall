package com.dmall.pay.service.impl.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.config.AliPayConfig;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/3 23:55
 */
@Component
public class AliCallBackHandler {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    public void callBack(HttpServletRequest request) {
        // 回调请求中获取支付宝参数
        String sign = request.getParameter("sign");
        String trade_no = request.getParameter("trade_no");
        String out_trade_no = request.getParameter("out_trade_no");
        String trade_status = request.getParameter("trade_status");
        String total_amount = request.getParameter("total_amount");
        String subject = request.getParameter("subject");
        String call_back_content = request.getQueryString();

        Map<String, String> params = new HashMap<>();
        //调用SDK验证签名
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.aliPay_public_key,
                    AliPayConfig.charset, AliPayConfig.sign_type);
            if (signVerified) {
                PaymentInfoDO paymentInfoDO = paymentInfoMapper.selectOne(Wrappers.<PaymentInfoDO>lambdaQuery()
                        .eq(PaymentInfoDO::getOrderId, Long.valueOf(out_trade_no)));
                if (paymentInfoDO == null) {

                }
                paymentInfoDO.setCallbackContent(call_back_content);
                paymentInfoDO.setCallBackTime(new Date());
                paymentInfoDO.setTradeNo(trade_no);
                paymentInfoDO.setPaymentStatus(PaymentStatusEnum.PAY_SUCCESS.getCode());
                paymentInfoMapper.updateById(paymentInfoDO);
            }
        } catch (AlipayApiException e) {
        }
    }
}

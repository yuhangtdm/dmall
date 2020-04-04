package com.dmall.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfig {

    /**
     * format
     */
    public final static String format = "json";

    /**
     * charset
     */
    public final static String charset = "utf-8";

    /**
     * sign_type
     */
    public final static String signType = "RSA2";

    /**
     * 支付宝的路径
     */
    public static String aliPayUrl;

    /**
     * 私钥
     */
    public static String appPrivateKey;

    /**
     * appId
     */
    public static String appId;

    /**
     * 同步地址
     */
    public static String returnPaymentUrl;

    /**
     * 异步通知地址
     */
    public static String notifyPaymentPrl;

    /**
     * 同步回调地址
     */
    public static String returnOrderUrl;

    /**
     * 支付宝的公钥
     */
    public static String aliPayPublicKey;

    @Value("${ali_pay_url}")
    public void setAliPayUrl(String aliPayUrl) {
        AliPayConfig.aliPayUrl = aliPayUrl;
    }

    @Value("${app_private_key}")
    public void setAppPrivateKey(String appPrivateKey) {
        AliPayConfig.appPrivateKey = appPrivateKey;
    }

    @Value("${app_id}")
    public void setAppId(String appId) {
        AliPayConfig.appId = appId;
    }

    @Value("${return_payment_url}")
    public void setReturnPaymentUrl(String returnPaymentUrl) {
        AliPayConfig.returnPaymentUrl = returnPaymentUrl;
    }

    @Value("${notify_payment_url}")
    public void setNotifyPaymentPrl(String notifyPaymentPrl) {
        AliPayConfig.notifyPaymentPrl = notifyPaymentPrl;
    }

    @Value("${return_order_url}")
    public void setReturnOrderUrl(String returnOrderUrl) {
        AliPayConfig.returnOrderUrl = returnOrderUrl;
    }

    @Value("${ali_pay_public_key}")
    public void setAliPayPublicKey(String aliPayPublicKey) {
        AliPayConfig.aliPayPublicKey = aliPayPublicKey;
    }

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(aliPayUrl, appId, appPrivateKey, format, charset, aliPayPublicKey, signType);
    }
}
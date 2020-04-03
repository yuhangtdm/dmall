package com.dmall.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfig {
    /**
     * 支付宝的路径
     */
    @Value("${ali_pay_url}")
    private String aliPayUrl;

    /**
     * 私钥
     */
    @Value("${app_private_key}")
    private String appPrivateKey;

    @Value("${app_id}")
    private String app_id;

    public final static String format = "json";

    public final static String charset = "utf-8";

    public final static String sign_type = "RSA2";

    /**
     * 同步地址
     */
    public static String return_payment_url;
    /**
     * 异步通知地址
     */
    public static String notify_payment_url;

    /**
     * 同步回调地址
     */
    public static String return_order_url;

    /**
     * 支付宝的公钥
     */
    public static String aliPay_public_key;

    @Value("${ali_pay_public_key}")
    public void setAlipay_public_key(String alipay_public_key) {
        AliPayConfig.aliPay_public_key = alipay_public_key;
    }

    @Value("${return_payment_url}")
    public void setReturn_url(String return_payment_url) {
        AliPayConfig.return_payment_url = return_payment_url;
    }

    @Value("${notify_payment_url}")
    public void setNotify_url(String notify_payment_url) {
        AliPayConfig.notify_payment_url = notify_payment_url;
    }

    @Value("${return_order_url}")
    public void setReturn_order_url(String return_order_url) {
        AliPayConfig.return_order_url = return_order_url;
    }

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(aliPayUrl, app_id, appPrivateKey, format, charset, aliPay_public_key, sign_type);
    }
}
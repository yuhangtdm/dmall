package com.dmall.pay.service.impl.handler;

import cn.hutool.core.util.NumberUtil;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResponseUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.config.AliPayConfig;
import com.dmall.pay.generator.dataobject.PaymentInfoDO;
import com.dmall.pay.generator.mapper.PaymentInfoMapper;
import com.dmall.pay.service.impl.AliTradeStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/3 23:55
 */
@Slf4j
@Component
public class AliCallBackHandler extends AbstractCommonHandler<Void, PaymentInfoDO, Void> {

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public BaseResult processor(Void aVoid) {
        HttpServletRequest request = RequestUtil.getRequest();
        HttpServletResponse response = ResponseUtil.getResponse();
        //调用SDK验证签名
        try {
            Map<String, String> param = RequestUtil.getParam();
            log.info("aliPay notify param:{}", param);
            boolean signVerified = AlipaySignature.rsaCheckV1(param, AliPayConfig.aliPayPublicKey,
                    AliPayConfig.charset, AliPayConfig.signType);
            if (signVerified) {
                // 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
                String out_trade_no = request.getParameter("out_trade_no");
                Long orderId = Long.valueOf(out_trade_no);
                PaymentInfoDO paymentInfoDO = paymentInfoMapper.selectOne(Wrappers.<PaymentInfoDO>lambdaQuery()
                        .eq(PaymentInfoDO::getOrderId, orderId));
                if (paymentInfoDO == null) {
                    log.error("out_trade_no not exists");
                    response.getWriter().println(FAIL);
                    return ResultUtil.fail();
                }
                // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
                String total_amount = request.getParameter("total_amount");
                if (!NumberUtil.equals(new BigDecimal(total_amount), paymentInfoDO.getAmount())) {
                    log.error("total_amount error,total_amount:{},amount:{}", total_amount, paymentInfoDO.getAmount());
                    response.getWriter().println(FAIL);
                    return ResultUtil.fail();
                }
                // 3、验证app_id是否为该商户本身。
                String auth_app_id = request.getParameter("auth_app_id");
                if (!Objects.equals(AliPayConfig.appId, auth_app_id)) {
                    log.error("appId error,auth_app_id:{},appId:{}", auth_app_id, AliPayConfig.appId);
                    response.getWriter().println(FAIL);
                    return ResultUtil.fail();
                }

                // 4、校验支付状态是否为待支付
                if (!paymentInfoDO.getPaymentStatus().equals(PaymentStatusEnum.WAIT_PAY.getCode())) {
                    log.error("PaymentStatus is not WAIT_PAY");
                    response.getWriter().println(FAIL);
                    return ResultUtil.fail();
                }
                // 5、获取交易状态为成功时修改表记录
                String trade_status = request.getParameter("trade_status");
                // 支付宝只有在支付成功后才会触发回调
                if (AliTradeStatusEnum.TRADE_SUCCESS.getCode().equals(trade_status)) {
                    String trade_no = request.getParameter("trade_no");
                    String call_back_content = request.getQueryString();
                    paymentInfoDO.setCallbackContent(call_back_content);
                    paymentInfoDO.setCallBackTime(new Date());
                    paymentInfoDO.setTradeNo(trade_no);
                    paymentInfoDO.setPaymentStatus(PaymentStatusEnum.PAY_SUCCESS.getCode());
                    paymentInfoMapper.updateById(paymentInfoDO);
                }
                // 发送消息到消息队列
                SendResult sendResult = rocketMQTemplate.syncSend(MqConstants.PAY_SUCCESS_TOPIC, orderId);
                if (SendStatus.SEND_OK == sendResult.getSendStatus()) {
                    response.getWriter().println(SUCCESS);
                } else {
                    log.error("send mq error");
                    response.getWriter().println(FAIL);
                }
            } else {
                log.error("signVerified error");
                response.getWriter().println(FAIL);
                return ResultUtil.fail();
            }
        } catch (Exception e) {
            log.error("notify error", e);
            try {
                response.getWriter().println(FAIL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ResultUtil.success();
    }

}

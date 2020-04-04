package com.dmall.pay.service.impl.handler.paytype.strategy;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.pay.api.dto.PayRequestDTO;
import com.dmall.pay.api.dto.PayResponseDTO;
import com.dmall.pay.api.enums.PayErrorEnum;
import com.dmall.pay.config.AliPayConfig;
import com.dmall.pay.service.impl.handler.paytype.AbstractPayTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付宝支付策略
 * @author: created by hang.yu on 2020/4/3 23:22
 */
@Slf4j
@Component
public class AliPayTypeStrategy extends AbstractPayTypeService {

    @Autowired
    private AlipayClient alipayClient;

    @Override
    public PayResponseDTO pay(PayRequestDTO requestDTO) {
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AliPayConfig.returnOrderUrl);
        aliPayRequest.setNotifyUrl(AliPayConfig.notifyPaymentPrl);

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", requestDTO.getOrderId());
        bizContent.put("total_amount", requestDTO.getAmount());
        bizContent.put("subject", requestDTO.getSubject());
        aliPayRequest.setBizContent(bizContent.toJSONString());

        String form = null;
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("create ali pay error", e);
            throw new BusinessException(PayErrorEnum.CREATE_ALI_ERROR);
        }
        PayResponseDTO payResponseDTO = new PayResponseDTO();
        payResponseDTO.setAliPayResult(form);
        payResponseDTO.setOrderId(requestDTO.getOrderId());
        payResponseDTO.setAmount(requestDTO.getAmount());
        return payResponseDTO;
    }
}

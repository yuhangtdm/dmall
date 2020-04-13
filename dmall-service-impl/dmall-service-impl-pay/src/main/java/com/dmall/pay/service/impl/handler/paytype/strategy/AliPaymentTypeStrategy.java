package com.dmall.pay.service.impl.handler.paytype.strategy;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.pay.api.dto.applyrefund.ApplyRefundRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentRequestDTO;
import com.dmall.pay.api.dto.createpayment.CreatePaymentResponseDTO;
import com.dmall.pay.api.enums.PaymentErrorEnum;
import com.dmall.pay.config.AliPayConfig;
import com.dmall.pay.service.impl.handler.paytype.AbstractPaymentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 支付宝支付策略
 * @author: created by hang.yu on 2020/4/3 23:22
 */
@Slf4j
@Component
public class AliPaymentTypeStrategy extends AbstractPaymentTypeService {

    @Autowired
    private AlipayClient alipayClient;

    // 出参 入参 记录日志
    @Override
    public CreatePaymentResponseDTO payment(CreatePaymentRequestDTO requestDTO) {
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
            throw new BusinessException(PaymentErrorEnum.CREATE_ALI_ERROR);
        }
        CreatePaymentResponseDTO createPaymentResponseDTO = new CreatePaymentResponseDTO();
        createPaymentResponseDTO.setPayResult(form);
        createPaymentResponseDTO.setOrderId(requestDTO.getOrderId());
        createPaymentResponseDTO.setAmount(requestDTO.getAmount());
        return createPaymentResponseDTO;
    }

    @Override
    public BaseResult<String> refund(ApplyRefundRequestDTO requestDTO, String tradeNo, Long refundRecordId) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        JSONObject bizContent = new JSONObject();
        // 订单号
        bizContent.put("out_trade_no", requestDTO.getOrderId());
        // 支付的交易编号
        bizContent.put("trade_no", tradeNo);
        // 退款金额
        bizContent.put("refund_amount", requestDTO.getAmount());
        // 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
        bizContent.put("out_request_no", refundRecordId);
        // 退款原因
        bizContent.put("refund_reason", requestDTO.getRefundReason());
        request.setBizContent(bizContent.toJSONString());
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                return ResultUtil.success(response.getTradeNo());
            } else {
                return ResultUtil.fail(PaymentErrorEnum.CREATE_ALI_ERROR);
            }
        }catch (AlipayApiException e){
            log.error("refund error", e);
            return ResultUtil.fail(PaymentErrorEnum.CREATE_ALI_ERROR);
        }
    }
}

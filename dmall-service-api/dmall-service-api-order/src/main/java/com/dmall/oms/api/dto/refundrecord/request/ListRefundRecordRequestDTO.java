package com.dmall.oms.api.dto.refundrecord.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 退款记录列表请求实体
 * @author: created by hang.yu on 2020-04-13 23:28:05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListRefundRecordRequestDTO", description = "退款记录列表请求实体")
public class ListRefundRecordRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "支付id", position = 2)
        private Long paymentId;

        @ApiModelProperty(value = "订单号", position = 3)
        private Long orderId;

        @ApiModelProperty(value = "子订单号", position = 4)
        private Long orderItemId;

        @ApiModelProperty(value = "子订单号", position = 5)
        private Long subOrderId;

        @ApiModelProperty(value = "退款金额", position = 6)
        private BigDecimal amount;

        @ApiModelProperty(value = "退款交易编号", position = 7)
        private String tradeNo;

        @ApiModelProperty(value = "交易内容", position = 8)
        private String subject;

        @ApiModelProperty(value = "退款状态 1-退款中;2-退款成功;3-退款失败", position = 9)
        private Integer status;







}

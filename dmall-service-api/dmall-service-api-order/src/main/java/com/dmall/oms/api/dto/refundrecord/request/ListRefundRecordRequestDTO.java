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
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListRefundRecordRequestDTO", description = "退款记录列表请求实体")
public class ListRefundRecordRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "订单id", position = 2)
        private Long orderId;

        @ApiModelProperty(value = "订单项id", position = 3)
        private Long orderItemId;

        @ApiModelProperty(value = "sku_id", position = 4)
        private Long skuId;

        @ApiModelProperty(value = "数量", position = 5)
        private Integer quantity;

        @ApiModelProperty(value = "金额", position = 6)
        private BigDecimal amout;

        @ApiModelProperty(value = "交易编号", position = 7)
        private String tradeNo;

        @ApiModelProperty(value = "交易内容", position = 8)
        private String subject;







}

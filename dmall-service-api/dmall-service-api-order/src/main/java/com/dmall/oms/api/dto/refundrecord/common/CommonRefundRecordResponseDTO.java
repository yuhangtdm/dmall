package com.dmall.oms.api.dto.refundrecord.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 退款记录公共响应实体
 * @author: created by hang.yu on 2020-04-13 23:28:05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonRefundRecordResponseDTO", description = "退款记录公共响应实体")
public class CommonRefundRecordResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

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

    @ApiModelProperty(value = "创建人", position = 10)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 11)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 12)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 13)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 Y,可用;N:不可用", position = 14)
    private String isDeleted;


}

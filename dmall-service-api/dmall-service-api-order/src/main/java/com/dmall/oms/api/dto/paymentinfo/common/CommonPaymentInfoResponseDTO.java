package com.dmall.oms.api.dto.paymentinfo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 支付信息公共响应实体
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonPaymentInfoResponseDTO", description = "支付信息公共响应实体")
public class CommonPaymentInfoResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "订单id", position = 2)
    private String orderId;

    @ApiModelProperty(value = "会员id", position = 3)
    private String memberId;

    @ApiModelProperty(value = "订单编号", position = 4)
    private String orderNo;

    @ApiModelProperty(value = "支付方式 1-支付宝;2-微信;3-银联", position = 5)
    private Integer paymentType;

    @ApiModelProperty(value = "交易编号", position = 6)
    private String tradeNo;

    @ApiModelProperty(value = "支付金额", position = 7)
    private BigDecimal amount;

    @ApiModelProperty(value = "交易内容", position = 8)
    private String subject;

    @ApiModelProperty(value = "支付状态 1-未支付;2-支付成功;3-支付失败", position = 9)
    private Integer paymentStatus;

    @ApiModelProperty(value = "回调信息", position = 10)
    private String callbackContent;

    @ApiModelProperty(value = "回调时间", position = 11)
    private String callbackTime;

    @ApiModelProperty(value = "创建人", position = 12)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 13)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 14)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 15)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 Y,可用;N:不可用", position = 16)
    private String isDeleted;


}

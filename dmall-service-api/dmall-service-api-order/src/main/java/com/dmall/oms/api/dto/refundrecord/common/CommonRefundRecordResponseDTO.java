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
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonRefundRecordResponseDTO", description = "退款记录公共响应实体")
public class CommonRefundRecordResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

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

    @ApiModelProperty(value = "创建人", position = 9)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 11)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 Y,可用;N:不可用", position = 13)
    private String isDeleted;


}

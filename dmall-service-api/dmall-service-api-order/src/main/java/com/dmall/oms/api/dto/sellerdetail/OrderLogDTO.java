package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.enums.OrderOperateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单日志信息
 * @author: created by hang.yu on 2020/4/7 23:10
 */
@Data
@ApiModel(value = "OrderLogDTO", description = "订单日志信息")
public class OrderLogDTO {

    @ApiModelProperty(value = "orderLogId", position = 1)
    private Long orderLogId;

    @ApiModelProperty(value = "orderId", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "子订单id", position = 3)
    private Long subOrderId;

    @ApiModelProperty(value = "操作", position = 4)
    private OrderOperateEnum operate;

    @ApiModelProperty(value = "操作人名称或用户名", position = 5)
    private String operator;

    @ApiModelProperty(value = "日志内容", position = 6)
    private String logContent;
}

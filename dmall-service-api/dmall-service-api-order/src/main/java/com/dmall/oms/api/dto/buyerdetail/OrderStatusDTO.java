package com.dmall.oms.api.dto.buyerdetail;

import com.dmall.oms.api.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 订单状态
 * @author: created by hang.yu on 2020/4/6 10:07
 */
@Data
@ApiModel(value = "OrderStatusDTO", description = "订单状态")
public class OrderStatusDTO implements Serializable {

    private static final long serialVersionUID = -647803450836672181L;

    @ApiModelProperty(value = "订单状态", position = 1)
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(value = "创建时间", position = 2)
    private Date createTime;
}

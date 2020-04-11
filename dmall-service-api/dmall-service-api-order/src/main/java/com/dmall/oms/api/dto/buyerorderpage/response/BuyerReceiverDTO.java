package com.dmall.oms.api.dto.buyerorderpage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 收货人响应实体
 * @author: created by hang.yu on 2020/4/11 21:00
 */
@Data
@ApiModel(value = "BuyerReceiverDTO", description = "收货人响应实体")
public class BuyerReceiverDTO {

    @ApiModelProperty(value = "收货人姓名", position = 1)
    private String receiverName;

    @ApiModelProperty(value = "收货人电话", position = 2)
    private String receiverPhone;

    @ApiModelProperty(value = "收货人详细地址", position = 3)
    private String receiverAddress;
}

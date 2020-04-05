package com.dmall.oms.api.dto.buyerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 收货人响应实体
 * @author: created by hang.yu on 2020/4/5 22:27
 */
@Data
@ApiModel(value = "ReceiverDTO", description = "收货人响应实体")
public class ReceiverDTO {

    @ApiModelProperty(value = "收货人姓名", position = 1)
    private String name;

    @ApiModelProperty(value = "手机号", position = 2)
    private String phone;

    @ApiModelProperty(value = "详细地址", position = 3)
    private String address;
}

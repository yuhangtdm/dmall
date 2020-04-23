package com.dmall.oms.api.dto.sellerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 收货人响应实体
 * @author: created by hang.yu on 2020/4/5 22:27
 */
@Data
@ApiModel(value = "SellerReceiverDTO", description = "收货人响应实体")
public class SellerReceiverDTO implements Serializable {

    private static final long serialVersionUID = -5329886026564755631L;

    @ApiModelProperty(value = "收货地址id", position = 1)
    private Long receiverId;

    @ApiModelProperty(value = "收货人姓名", position = 2)
    private String receiverName;

    @ApiModelProperty(value = "收货人电话", position = 3)
    private String receiverPhone;

    @ApiModelProperty(value = " 收货人省份/直辖市", position = 4)
    private String receiverProvince;

    @ApiModelProperty(value = "收货人城市", position = 5)
    private String receiverCity;

    @ApiModelProperty(value = "收货人区/县", position = 6)
    private String receiverRegion;

    @ApiModelProperty(value = "收货人详细地址", position = 7)
    private String receiverDetailAddress;
}

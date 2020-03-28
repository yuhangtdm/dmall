package com.dmall.oms.api.dto.createorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 地址实体
 * @author: created by hang.yu on 2020/3/26 22:41
 */
@Data
@ApiModel(value = "OrderAddressRequestDTO", description = "提交订单地址实体")
public class OrderAddressRequestDTO implements Serializable {

    private static final long serialVersionUID = 2960217748306337787L;

    @ApiModelProperty(value = "id", position = 1)
    @NotNull(message = "收货地址id不能为空")
    private Long id;

    @ApiModelProperty(value = "收货人姓名", position = 2)
    @NotBlank(message = "收货人姓名不能为空")
    private String name;

    @ApiModelProperty(value = "手机号", position = 3)
    @NotBlank(message = "收货人手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "所在省", position = 4)
    @NotBlank(message = "收货人省份不能为空")
    private String province;

    @ApiModelProperty(value = "所在市", position = 5)
    @NotBlank(message = "收货人城市不能为空")
    private String city;

    @ApiModelProperty(value = "所在区/县", position = 6)
    @NotBlank(message = "收货人区/县不能为空")
    private String region;

    @ApiModelProperty(value = "详细地址", position = 7)
    @NotBlank(message = "收货人详细地址不能为空")
    private String detailAddress;

}

package com.dmall.mms.api.dto.memberreceiveaddress.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 会员地址响应实体
 * @author: created by hang.yu on 2020/2/29 15:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ReceiveAddressResponseDTO", description = "会员地址响应实体")
public class ReceiveAddressResponseDTO implements Serializable {

    private static final long serialVersionUID = 3757275876060653032L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "收货人", position = 2)
    private String name;

    @ApiModelProperty(value = "手机号", position = 3)
    private String phone;

    @ApiModelProperty(value = "是否为默认地址 Y-是;N-否,注意只有一个默认地址", position = 5)
    private String defaultStatus;

    @ApiModelProperty(value = "所在省", position = 6)
    private String province;

    @ApiModelProperty(value = "所在市", position = 7)
    private String city;

    @ApiModelProperty(value = "所在区/县", position = 8)
    private String region;

    @ApiModelProperty(value = "详细地址", position = 9)
    private String detailAddress;

    @ApiModelProperty(value = "会员id", position = 10)
    private Long creator;

    @ApiModelProperty(value = "邮箱", position = 11)
    private String email;

}

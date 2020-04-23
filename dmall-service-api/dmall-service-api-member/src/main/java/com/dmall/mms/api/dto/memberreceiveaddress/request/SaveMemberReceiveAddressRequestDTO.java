package com.dmall.mms.api.dto.memberreceiveaddress.request;

import com.dmall.common.dto.validate.PhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增会员收货地址请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@ApiModel(value = "SaveMemberReceiveAddressRequestDTO", description = "新增会员收货地址请求实体")
public class SaveMemberReceiveAddressRequestDTO implements Serializable {

    private static final long serialVersionUID = 8259425929463089728L;

    @ApiModelProperty(value = "收货人", position = 1, required = true)
    @NotBlank(message = "收货人不能为空")
    private String name;

    @ApiModelProperty(value = "手机号", position = 2, required = true)
    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 3)
    @Email(message = "邮箱不合法")
    private String email;

    @ApiModelProperty(value = "所在省", position = 4, required = true)
    @NotBlank(message = "所在省不能为空")
    private String province;

    @ApiModelProperty(value = "所在市", position = 5, required = true)
    @NotBlank(message = "所在市不能为空")
    private String city;

    @ApiModelProperty(value = "所在区/县", position = 6, required = true)
    @NotBlank(message = "所在区/县不能为空")
    private String region;

    @ApiModelProperty(value = "详细地址", position = 7, required = true)
    @NotBlank(message = "详细地址不能为空")
    private String detailAddress;
}

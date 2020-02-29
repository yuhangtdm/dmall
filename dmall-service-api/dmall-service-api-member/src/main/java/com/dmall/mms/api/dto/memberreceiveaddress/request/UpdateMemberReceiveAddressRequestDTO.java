package com.dmall.mms.api.dto.memberreceiveaddress.request;

import com.dmall.common.dto.validate.PhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改会员收货地址请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateMemberReceiveAddressRequestDTO", description = "修改会员收货地址请求实体")
public class UpdateMemberReceiveAddressRequestDTO implements Serializable {

    private static final long serialVersionUID = -1519261478105525894L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "收货人", position = 2)
    private String name;

    @ApiModelProperty(value = "手机号", position = 3)
    @PhoneNumber
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 3)
    @Email(message = "邮箱不合法")
    private String email;

    @ApiModelProperty(value = "所在省", position = 4)
    private String province;

    @ApiModelProperty(value = "所在市", position = 5)
    private String city;

    @ApiModelProperty(value = "所在区/县", position = 9)
    private String region;

    @ApiModelProperty(value = "详细地址", position = 10)
    private String detailAddress;

}

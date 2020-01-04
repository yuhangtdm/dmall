package com.dmall.mms.api.dto.memberreceiveaddress.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 会员收货地址公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMemberReceiveAddressResponseDTO", description = "会员收货地址公共响应实体")
public class CommonMemberReceiveAddressResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;


    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "姓名", position = 3)
    private String name;


    @ApiModelProperty(value = "手机号", position = 4)
    private String phone;


    @ApiModelProperty(value = "是否为默认地址 Y-是;N-否,注意只有一个默认地址", position = 5)
    private String defaultStatus;


    @ApiModelProperty(value = "邮政编码", position = 6)
    private String postCode;


    @ApiModelProperty(value = "所在省", position = 7)
    private String province;


    @ApiModelProperty(value = "所在市", position = 8)
    private String city;


    @ApiModelProperty(value = "所在区/县", position = 9)
    private String region;


    @ApiModelProperty(value = "详细地址", position = 10)
    private String detailAddress;


    @ApiModelProperty(value = "创建人", position = 11)
    private Long creator;


    @ApiModelProperty(value = "创建时间", position = 12)
    private Date gmtCreated;


    @ApiModelProperty(value = "更新人", position = 13)
    private Long modifier;


    @ApiModelProperty(value = "更新时间", position = 14)
    private Date gmtModified;


    @ApiModelProperty(value = "状态 Y-可用;N-不可用", position = 15)
    private String isDeleted;


}

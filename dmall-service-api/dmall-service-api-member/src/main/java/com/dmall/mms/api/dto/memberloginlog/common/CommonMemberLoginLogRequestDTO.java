package com.dmall.mms.api.dto.memberloginlog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 会员登录记录公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:18
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonMemberLoginLogRequestDTO", description="会员登录记录公共请求实体")
public class CommonMemberLoginLogRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "member_id", position = 2)
    private String memberId;


    @ApiModelProperty(value = "token", position = 3)
    private String token;


    @ApiModelProperty(value = "ip地址", position = 4)
    private String ip;


    @ApiModelProperty(value = "登录城市", position = 5)
    private String city;


    @ApiModelProperty(value = "登录类型 1-PC;2-android;3-ios;4-小程序", position = 6)
    private String type;












}

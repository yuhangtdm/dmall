package com.dmall.mms.api.dto.memberloginlog.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员登录记录列表请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMemberLoginLogRequestDTO", description = "会员登录记录列表请求实体")
public class ListMemberLoginLogRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "token", position = 3)
        private String token;

        @ApiModelProperty(value = "ip地址", position = 4)
        private String ip;

        @ApiModelProperty(value = "登录城市", position = 5)
        private String city;

        @ApiModelProperty(value = "登录类型 1-PC;2-android;3-ios;4-小程序", position = 6)
        private String type;







}

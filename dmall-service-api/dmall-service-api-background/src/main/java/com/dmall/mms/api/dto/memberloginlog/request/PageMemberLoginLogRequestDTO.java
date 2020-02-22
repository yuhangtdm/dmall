package com.dmall.mms.api.dto.memberloginlog.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 会员登录记录分页请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageMemberLoginLogRequestDTO", description =  "会员登录记录分页请求实体")
public class PageMemberLoginLogRequestDTO  extends PageRequestDTO {



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

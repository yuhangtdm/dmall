package com.dmall.mms.api.dto.membersafe.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 账户安全列表请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMemberSafeRequestDTO", description = "账户安全列表请求实体")
public class ListMemberSafeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "会员id", position = 2)
        private Long memberId;

        @ApiModelProperty(value = "是否绑定手机号 Y-可用;N-不可用", position = 3)
        private String bindMobile;

        @ApiModelProperty(value = "是否设置密码 Y-可用;N-不可用", position = 4)
        private String setPassword;

        @ApiModelProperty(value = "是否绑定邮箱 Y-可用;N-不可用", position = 5)
        private String bindEmail;

        @ApiModelProperty(value = "是否实名认证 Y-可用;N-不可用", position = 6)
        private String realNameAuthentication;

        @ApiModelProperty(value = "是否锁定账号 Y-可用;N-不可用", position = 7)
        private String lockAccount;







}

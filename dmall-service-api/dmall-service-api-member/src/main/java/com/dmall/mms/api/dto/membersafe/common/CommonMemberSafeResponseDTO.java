package com.dmall.mms.api.dto.membersafe.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 账户安全公共响应实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMemberSafeResponseDTO", description = "账户安全公共响应实体")
public class CommonMemberSafeResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

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

    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 Y-不可用;N-可用", position = 12)
    private String isDeleted;


}

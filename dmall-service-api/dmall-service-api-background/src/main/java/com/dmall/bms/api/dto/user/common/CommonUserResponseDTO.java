package com.dmall.bms.api.dto.user.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonUserResponseDTO", description = "后台用户公共响应实体")
public class CommonUserResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商家店铺id", position = 2)
    private Long merchantsId;

    @ApiModelProperty(value = "用户名", position = 3)
    private String userName;

    @ApiModelProperty(value = "昵称", position = 4)
    private String nickName;

    @ApiModelProperty(value = "手机号", position = 5)
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 6)
    private String email;

    @ApiModelProperty(value = "密码", position = 7)
    private String password;

    @ApiModelProperty(value = "真实姓名", position = 8)
    private String realName;

    @ApiModelProperty(value = "头像", position = 9)
    private String icon;

    @ApiModelProperty(value = "备注", position = 10)
    private String remark;

    @ApiModelProperty(value = "创建人", position = 11)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 12)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 13)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 14)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 15)
    private String isDeleted;

}

package com.dmall.demo.api.dto.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户响应实体
 * @author: created by hang.yu on 2020-04-19 21:30:17
 */
@Data
@ApiModel(value = "UserResponseDTO", description = "后台用户响应实体")
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商家店铺id", position = 2)
    private Long merchantsId;

    @ApiModelProperty(value = "仓库id", position = 3)
    private Long warehouseId;

    @ApiModelProperty(value = "用户名", position = 4)
    private String userName;

    @ApiModelProperty(value = "昵称", position = 5)
    private String nickName;

    @ApiModelProperty(value = "手机号", position = 6)
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 7)
    private String email;

    @ApiModelProperty(value = "密码", position = 8)
    private String password;

    @ApiModelProperty(value = "真实姓名", position = 9)
    private String realName;

    @ApiModelProperty(value = "头像", position = 10)
    private String icon;

    @ApiModelProperty(value = "备注", position = 11)
    private String remark;

    private BigDecimal money;

    @ApiModelProperty(value = "创建人", position = 13)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 14)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 15)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 16)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 17)
    private String isDeleted;

}

package com.dmall.bms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 后台用户登录日志表
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLoginLogDTO", description = "后台用户登录日志表")
public class UserLoginLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "ip地址", position = 3)
    private String ip;

    @ApiModelProperty(value = "城市", position = 4)
    private String city;

    @ApiModelProperty(value = "浏览器登录类型", position = 5)
    private String userAgent;

    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 10)
    private String isDeleted;

}

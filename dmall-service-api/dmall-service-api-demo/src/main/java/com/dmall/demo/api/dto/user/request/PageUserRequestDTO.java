package com.dmall.demo.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;

import java.util.*;
import java.math.*;

/**
 * @description: 后台用户分页请求实体
 * @author: created by hang.yu on 2020-04-19 21:30:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageUserRequestDTO", description = "后台用户分页请求实体")
public class PageUserRequestDTO extends PageRequestDTO {

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

}

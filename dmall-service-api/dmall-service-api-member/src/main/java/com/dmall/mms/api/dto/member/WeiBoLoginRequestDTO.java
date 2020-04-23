package com.dmall.mms.api.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 微博登录请求实体
 * @author: created by hang.yu on 2020/3/1 21:28
 */
@Data
@ApiModel(value = "WeiBoLoginRequestDTO", description = "微博登录请求实体")
public class WeiBoLoginRequestDTO implements Serializable {
    private static final long serialVersionUID = -4637841047425214028L;

    @ApiModelProperty(value = "微博号", position = 1, required = true)
    @NotNull(message = "微博号不能为空")
    private Long weiBoNo;

    @ApiModelProperty(value = "微博名称", position = 2, required = true)
    @NotBlank(message = "微博名称不能为空")
    private String name;

    @ApiModelProperty(value = "微博昵称", position = 3)
    private String nickName;

    @ApiModelProperty(value = "头像", position = 4)
    private String icon;

    @ApiModelProperty(value = "性别", position = 5)
    private String gender;
}

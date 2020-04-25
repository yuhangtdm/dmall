package com.dmall.sso.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @description: 后台登录响应实体
 * @author: created by hang.yu on 2020/1/6 23:10
 */
@Data
@ApiModel(value = "AdminLoginResponseDTO", description = "后台登录响应实体")
public class AdminLoginResponseDTO {

    @ApiModelProperty(value = "token", position = 1)
    private String token;

    @ApiModelProperty(value = "url", position = 2)
    private String url;

}

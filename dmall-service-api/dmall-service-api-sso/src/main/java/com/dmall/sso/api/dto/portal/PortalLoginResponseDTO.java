package com.dmall.sso.api.dto.portal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 后台登录响应实体
 * @author: created by hang.yu on 2020/1/6 23:10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PortalLoginResponseDTO", description = "商城登录响应实体")
public class PortalLoginResponseDTO {

    @ApiModelProperty(value = "token", position = 1)
    private String token;

    @ApiModelProperty(value = "url", position = 2)
    private String url;

}

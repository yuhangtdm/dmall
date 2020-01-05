package com.dmall.bms.api.dto.userloginlog.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;

import java.util.*;
import java.math.*;

/**
 * @description: 后台用户登录日志分页请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageUserLoginLogRequestDTO", description = "后台用户登录日志分页请求实体")
public class PageUserLoginLogRequestDTO extends PageRequestDTO {


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "ip地址", position = 3)
    private String ip;

    @ApiModelProperty(value = "城市", position = 4)
    private String city;

    @ApiModelProperty(value = "浏览器登录类型", position = 5)
    private String userAgent;


}

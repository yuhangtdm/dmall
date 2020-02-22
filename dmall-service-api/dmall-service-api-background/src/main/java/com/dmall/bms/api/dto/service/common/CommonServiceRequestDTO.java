package com.dmall.bms.api.dto.service.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 服务表 公共请求实体
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonServiceRequestDTO", description = "服务表 公共请求实体")
public class CommonServiceRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "app_id", position = 2)
    private String appId;

    @ApiModelProperty(value = "名称", position = 3)
    private String name;







}

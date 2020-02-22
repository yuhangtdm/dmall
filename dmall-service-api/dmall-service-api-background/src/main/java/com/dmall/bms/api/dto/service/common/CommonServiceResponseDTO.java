package com.dmall.bms.api.dto.service.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 服务表 公共响应实体
 * @author: created by hang.yu on 2020-02-20 21:36:44
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonServiceResponseDTO", description = "服务表 公共响应实体")
public class CommonServiceResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "app_id", position = 2)
    private String appId;

    @ApiModelProperty(value = "名称", position = 3)
    private String name;

    @ApiModelProperty(value = "创建人", position = 4)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 5)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 6)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 7)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 8)
    private String isDeleted;


}

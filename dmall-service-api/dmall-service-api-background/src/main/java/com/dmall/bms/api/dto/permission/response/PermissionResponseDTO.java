package com.dmall.bms.api.dto.permission.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 权限公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "PermissionResponseDTO", description = "权限响应实体")
public class PermissionResponseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "父级id", position = 2)
    private Integer parentId;

    @ApiModelProperty(value = "权限码", position = 3)
    private String code;

    @ApiModelProperty(value = "权限名称", position = 4)
    private String name;

    @ApiModelProperty(value = "1-接口地址;2-目录;3-菜单", position = 5)
    private Integer type;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 6)
    private String method;

    @ApiModelProperty(value = "图标", position = 8)
    private String icon;

    @ApiModelProperty(value = "创建人", position = 9)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 11)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 13)
    private String isDeleted;

}

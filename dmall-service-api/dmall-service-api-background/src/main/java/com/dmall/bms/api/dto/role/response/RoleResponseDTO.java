package com.dmall.bms.api.dto.role.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 后台角色公共响应实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "RoleResponseDTO", description = "后台角色分页响应实体")
public class RoleResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

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

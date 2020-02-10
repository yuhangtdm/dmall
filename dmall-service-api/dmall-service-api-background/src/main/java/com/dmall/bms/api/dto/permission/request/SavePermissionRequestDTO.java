package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.dto.permission.enums.HttpMethodEnum;
import com.dmall.bms.api.dto.permission.enums.PermissionTypeEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.permission.common.CommonPermissionRequestDTO;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增权限请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SavePermissionRequestDTO", description = "新增权限请求实体")
public class SavePermissionRequestDTO implements Serializable {

    @ApiModelProperty(value = "目录id", position = 1)
    private Long parentId;

    @ApiModelProperty(value = "权限码", position = 2)
    @NotBlank(message = "权限码不能为空")
    private String code;

    @ApiModelProperty(value = "权限名称", position = 4)
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "权限类型：1-接口地址;2-目录;3-菜单", position = 5)
    @NotNull(message = "权限类型不能为空")
    @ValueInEnum(PermissionTypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "权限地址", position = 6)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 7)
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

    @ApiModelProperty(value = "图标", position = 8)
    private String icon;
}

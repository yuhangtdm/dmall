package com.dmall.bms.api.dto.role.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 后台角色分页请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageRoleRequestDTO", description = "后台角色分页请求实体")
public class PageRoleRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "角色名称", position = 5)
    private String name;

}

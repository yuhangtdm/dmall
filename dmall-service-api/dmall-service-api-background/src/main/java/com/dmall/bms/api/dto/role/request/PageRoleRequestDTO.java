package com.dmall.bms.api.dto.role.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;

import java.util.*;
import java.math.*;

/**
 * @description: 后台角色分页请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageRoleRequestDTO", description = "后台角色分页请求实体")
public class PageRoleRequestDTO extends PageRequestDTO {


    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    @ApiModelProperty(value = "备注", position = 3)
    private String remark;


}

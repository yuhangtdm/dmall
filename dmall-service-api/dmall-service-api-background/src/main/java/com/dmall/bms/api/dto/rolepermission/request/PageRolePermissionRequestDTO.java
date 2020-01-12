package com.dmall.bms.api.dto.rolepermission.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;

/**
 * @description: 后台角色-资源分页请求实体
 * @author: created by hang.yu on 2020-01-11 18:47:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageRolePermissionRequestDTO", description = "后台角色-资源分页请求实体")
public class PageRolePermissionRequestDTO  extends PageRequestDTO{



        @ApiModelProperty(value = "角色id", position = 2)
    private Long roleId;

        @ApiModelProperty(value = "资源id", position = 3)
    private Long resourceId;







}

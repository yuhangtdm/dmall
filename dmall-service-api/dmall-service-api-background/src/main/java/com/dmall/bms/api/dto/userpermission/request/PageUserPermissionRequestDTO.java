package com.dmall.bms.api.dto.userpermission.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限分页请求实体
 * @author: created by hang.yu on 2020-01-11 19:13:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageUserPermissionRequestDTO", description =  "后台用户和权限关系表")
public class PageUserPermissionRequestDTO  extends PageRequestDTO {


        @ApiModelProperty(value = "用户id", position = 2)
        private Long userId;

        @ApiModelProperty(value = "资源id", position = 3)
        private Long permissionId;

        @ApiModelProperty(value = "类型 +:增加的权限;-:减少的权限", position = 4)
        private String type;







}

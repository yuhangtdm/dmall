package com.dmall.bms.api.dto.userrole.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;

/**
 * @description: 后台用户-角色分页请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageUserRoleRequestDTO", description = "后台用户-角色分页请求实体")
public class PageUserRoleRequestDTO extends PageRequestDTO {


    @ApiModelProperty(value = "用户id", position = 2)
    private Long userId;

    @ApiModelProperty(value = "角色id", position = 3)
    private Long roleId;


}

package com.dmall.sso.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.sso.api.dto.admin.PermissionResponseDTO;
import com.dmall.sso.api.dto.admin.RoleResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @description: AdminPermissionService
 * @author: created by hang.yu on 2020/1/11 22:16
 */
@Api(tags = "后台登录服务")
@Validated
@RequestMapping("/admin")
public interface AdminPermissionService {

    /**
     * 获取角色列表
     */
    @GetMapping("/listRoles")
    @ApiOperation(value = "获取角色列表")
    BaseResult<List<RoleResponseDTO>> listRoles(@NotBlank(message = "用户名称不能为空") @RequestParam String userName);

    /**
     * 获取权限列表
     */
    @GetMapping("/listPermissions")
    @ApiOperation(value = "获取权限列表")
    BaseResult<List<PermissionResponseDTO>> listPermissions(@NotBlank(message = "用户名称不能为空") @RequestParam String userName);
}

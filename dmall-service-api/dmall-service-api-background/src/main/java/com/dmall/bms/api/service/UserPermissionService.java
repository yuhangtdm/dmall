package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.userpermission.request.ListUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.request.PageUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.common.CommonUserPermissionResponseDTO;
import com.dmall.bms.api.dto.userpermission.request.SaveUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.request.UpdateUserPermissionRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Api(tags = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务")
@RequestMapping("/userPermission")
public interface UserPermissionService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    BaseResult<Long> save(@Valid @RequestBody SaveUserPermissionRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    @ApiImplicitParam(name = "id", value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserPermissionRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    @ApiImplicitParam(name = "id", value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonUserPermissionResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限列表")
    BaseResult<List<CommonUserPermissionResponseDTO>>list(@RequestBody ListUserPermissionRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限分页")
    BaseResult<LayUiPage<CommonUserPermissionResponseDTO>>page(@RequestBody PageUserPermissionRequestDTO requestDTO);

}

package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.rolepermission.request.ListRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.request.PageRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.common.CommonRolePermissionResponseDTO;
import com.dmall.bms.api.dto.rolepermission.request.SaveRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.request.UpdateRolePermissionRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台角色-资源服务
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Api(tags = "后台角色-资源服务")
@RequestMapping("/rolePermission")
public interface RolePermissionService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台角色-资源")
    BaseResult<Long> save(@Valid @RequestBody SaveRolePermissionRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台角色-资源")
    @ApiImplicitParam(name = "id", value = "后台角色-资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台角色-资源")
    BaseResult<Long> update(@Valid @RequestBody UpdateRolePermissionRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台角色-资源")
    @ApiImplicitParam(name = "id", value = "后台角色-资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonRolePermissionResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台角色-资源列表")
    BaseResult<List<CommonRolePermissionResponseDTO>>list(@RequestBody ListRolePermissionRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台角色-资源分页")
    BaseResult<LayUiPage<CommonRolePermissionResponseDTO>>page(@RequestBody PageRolePermissionRequestDTO requestDTO);

}

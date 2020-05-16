package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.permission.request.ListPermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.PagePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.SavePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.UpdatePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.response.PermissionResponseDTO;
import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 权限服务
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Api(tags = "权限服务")
@RequestMapping("/permission")
public interface PermissionService {

    @PostMapping
    @ApiOperation(value = "新增权限")
    BaseResult<Long> save(@Valid @RequestBody SavePermissionRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除权限")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改权限")
    BaseResult<Long> update(@Valid @RequestBody UpdatePermissionRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询权限")
    @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<PermissionResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "权限列表")
    BaseResult<List<PermissionResponseDTO>> list(@RequestBody ListPermissionRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "权限分页")
    BaseResult<ResponsePage<PermissionResponseDTO>> page(@RequestBody PagePermissionRequestDTO requestDTO);

    @GetMapping("/tab/{id}")
    @ApiOperation(value = "权限tab")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<List<TabPermissionResponseDTO>> tab(@PathVariable("id") Long id);

    @GetMapping("/importAll")
    @ApiOperation(value = "导入所有权限")
    BaseResult importAll();
}

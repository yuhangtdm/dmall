package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.role.request.*;
import com.dmall.bms.api.dto.role.response.RoleResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.TreeCheckedDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台角色服务
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Api(tags = "后台角色服务")
@RequestMapping("/role")
public interface RoleService {

    @PostMapping
    @ApiOperation(value = "新增后台角色")
    BaseResult<Long> save(@Valid @RequestBody SaveRoleRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台角色")
    @ApiImplicitParam(name = "id", value = "后台角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改后台角色")
    BaseResult<Long> update(@Valid @RequestBody UpdateRoleRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台角色")
    @ApiImplicitParam(name = "id", value = "后台角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<RoleResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台角色列表")
    BaseResult<List<RoleResponseDTO>> list(@RequestBody ListRoleRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台角色分页")
    BaseResult<ResponsePage<RoleResponseDTO>> page(@RequestBody PageRoleRequestDTO requestDTO);

    @PostMapping("/setPermission")
    @ApiOperation(value = "设置权限")
    BaseResult<Long> setPermission(@Valid @RequestBody TreeCheckedDTO requestDTO);

    @PostMapping("/setMenu")
    @ApiOperation(value = "设置菜单")
    BaseResult<Long> setMenu(@Valid @RequestBody TreeCheckedDTO requestDTO);

    @GetMapping("/getMenus/{id}")
    @ApiOperation(value = "根据角色id查询菜单列表")
    @ApiImplicitParam(name = "id", value = "后台角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<List<Long>> getMenus(@PathVariable("id") Long id);
}

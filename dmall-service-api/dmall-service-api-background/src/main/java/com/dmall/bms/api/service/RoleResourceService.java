package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.roleresource.request.ListRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.request.PageRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.common.CommonRoleResourceResponseDTO;
import com.dmall.bms.api.dto.roleresource.request.SaveRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.request.UpdateRoleResourceRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台角色-资源服务
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Api(tags = "后台角色-资源服务")
@RequestMapping("/roleResource")
public interface RoleResourceService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台角色-资源")
    BaseResult<Long> save(@Valid @RequestBody SaveRoleResourceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台角色-资源")
    @ApiImplicitParam(name = "id", value = "后台角色-资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台角色-资源")
    BaseResult<Long> update(@Valid @RequestBody UpdateRoleResourceRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台角色-资源")
    @ApiImplicitParam(name = "id", value = "后台角色-资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonRoleResourceResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台角色-资源列表")
    BaseResult<List<CommonRoleResourceResponseDTO>> list(@RequestBody ListRoleResourceRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台角色-资源分页")
    BaseResult<LayuiPage<CommonRoleResourceResponseDTO>> page(@RequestBody PageRoleResourceRequestDTO requestDTO);

}

package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.role.request.ListRoleRequestDTO;
import com.dmall.bms.api.dto.role.request.PageRoleRequestDTO;
import com.dmall.bms.api.dto.role.common.CommonRoleResponseDTO;
import com.dmall.bms.api.dto.role.request.SaveRoleRequestDTO;
import com.dmall.bms.api.dto.role.request.UpdateRoleRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台角色服务
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Api(tags = "后台角色服务")
@RequestMapping("/role")
public interface RoleService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台角色")
    BaseResult<Long> save(@Valid @RequestBody SaveRoleRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台角色")
    @ApiImplicitParam(name = "id", value = "后台角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台角色")
    BaseResult<Long> update(@Valid @RequestBody UpdateRoleRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台角色")
    @ApiImplicitParam(name = "id", value = "后台角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonRoleResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台角色列表")
    BaseResult<List<CommonRoleResponseDTO>> list(@RequestBody ListRoleRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台角色分页")
    BaseResult<LayUiPage<CommonRoleResponseDTO>> page(@RequestBody PageRoleRequestDTO requestDTO);

}

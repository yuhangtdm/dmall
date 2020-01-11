package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.userrole.request.ListUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.request.PageUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.common.CommonUserRoleResponseDTO;
import com.dmall.bms.api.dto.userrole.request.SaveUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.request.UpdateUserRoleRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台用户-角色服务
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Api(tags = "后台用户-角色服务")
@RequestMapping("/userRole")
public interface UserRoleService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台用户-角色")
    BaseResult<Long> save(@Valid @RequestBody SaveUserRoleRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台用户-角色")
    @ApiImplicitParam(name = "id", value = "后台用户-角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台用户-角色")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserRoleRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户-角色")
    @ApiImplicitParam(name = "id", value = "后台用户-角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonUserRoleResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台用户-角色列表")
    BaseResult<List<CommonUserRoleResponseDTO>> list(@RequestBody ListUserRoleRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户-角色分页")
    BaseResult<LayUiPage<CommonUserRoleResponseDTO>> page(@RequestBody PageUserRoleRequestDTO requestDTO);

}

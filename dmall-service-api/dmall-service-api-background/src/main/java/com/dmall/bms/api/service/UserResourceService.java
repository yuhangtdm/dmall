package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.userresource.request.ListUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.request.PageUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.common.CommonUserResourceResponseDTO;
import com.dmall.bms.api.dto.userresource.request.SaveUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.request.UpdateUserResourceRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Api(tags = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务")
@RequestMapping("/userResource")
public interface UserResourceService {

    @PostMapping("/")
    @ApiOperation(value = "新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    BaseResult<Long> save(@Valid @RequestBody SaveUserResourceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    @ApiImplicitParam(name = "id", value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserResourceRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限")
    @ApiImplicitParam(name = "id", value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonUserResourceResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限列表")
    BaseResult<List<CommonUserResourceResponseDTO>> list(@RequestBody ListUserResourceRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限分页")
    BaseResult<LayUiPage<CommonUserResourceResponseDTO>> page(@RequestBody PageUserResourceRequestDTO requestDTO);

}

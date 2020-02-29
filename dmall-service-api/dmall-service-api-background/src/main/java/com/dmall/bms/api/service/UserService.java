package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.user.request.*;
import com.dmall.bms.api.dto.user.common.CommonUserResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 后台用户服务
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Api(tags = "后台用户服务")
@Validated
@RequestMapping("/user")
public interface UserService {

    @PostMapping
    @ApiOperation(value = "新增后台用户")
    BaseResult<Long> save(@Valid @RequestBody SaveUserRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改后台用户")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonUserResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户分页")
    BaseResult<ResponsePage<CommonUserResponseDTO>> page(@RequestBody PageUserRequestDTO requestDTO);

    @ApiOperation(value = "上传用户头像")
    @PostMapping("/uploadIcon")
    BaseResult<UploadResult> uploadIcon(@NotNull(message = "头像不能为空") MultipartFile file);

    @ApiOperation(value = "设置角色")
    @PostMapping("/setRoles")
    BaseResult<Long> setRoles(@Valid @RequestBody SetRolesRequestDTO requestDTO);

    @ApiOperation(value = "设置权限")
    @PostMapping("/setPermissions")
    BaseResult<Long> setPermissions(@Valid @RequestBody SetPermissionsExtRequestDTO requestDTO);
}

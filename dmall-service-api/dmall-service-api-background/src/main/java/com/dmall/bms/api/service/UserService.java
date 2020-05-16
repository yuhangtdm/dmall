package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.bms.api.dto.user.request.PageUserRequestDTO;
import com.dmall.bms.api.dto.user.request.SaveUserRequestDTO;
import com.dmall.bms.api.dto.user.request.UpdatePasswordRequestDTO;
import com.dmall.bms.api.dto.user.request.UpdateUserRequestDTO;
import com.dmall.bms.api.dto.user.response.UserResponseDTO;
import com.dmall.bms.api.dto.user.response.UserRoleResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.CheckedDTO;
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
import java.util.List;

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

    @GetMapping("/disable/{id}")
    @ApiOperation(value = "启用后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> disable(@PathVariable("id") Long id);

    @GetMapping("/enable/{id}")
    @ApiOperation(value = "启用后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> enable(@PathVariable("id") Long id);

    @GetMapping("/resetPassword/{id}")
    @ApiOperation(value = "重置密码")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> resetPassword(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改后台用户")
    BaseResult<Long> update(@Valid @RequestBody UpdateUserRequestDTO requestDTO);

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    BaseResult<Long> updatePassword(@Valid @RequestBody UpdatePasswordRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<UserResponseDTO> get(@PathVariable("id") Long id);

    @GetMapping
    @ApiOperation(value = "根据id查询后台用户")
    BaseResult<UserResponseDTO> getCurrentUser();

    @PostMapping("/page")
    @ApiOperation(value = "后台用户分页")
    BaseResult<ResponsePage<UserResponseDTO>> page(@RequestBody PageUserRequestDTO requestDTO);

    @ApiOperation(value = "上传用户头像")
    @PostMapping("/uploadIcon")
    BaseResult<UploadResult> uploadIcon(@NotNull(message = "头像不能为空") MultipartFile file);

    @ApiOperation(value = "设置角色")
    @PostMapping("/setRole")
    BaseResult<Long> setRole(@Valid @RequestBody CheckedDTO requestDTO);

    @ApiOperation(value = "设置权限")
    @PostMapping("/setPermission")
    BaseResult<Long> setPermission(@Valid @RequestBody CheckedDTO requestDTO);

    @GetMapping("/roleList/{id}")
    @ApiOperation(value = "根据id查询后台用户")
    @ApiImplicitParam(name = "id", value = "后台用户id", required = true, dataType = "int", paramType = "path")
    BaseResult<List<UserRoleResponseDTO>> roleList(@PathVariable("id") Long id);

    @GetMapping("/tab/{id}")
    @ApiOperation(value = "权限tab")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int", paramType = "path")
    BaseResult<List<TabPermissionResponseDTO>> tab(@PathVariable("id") Long id);
}

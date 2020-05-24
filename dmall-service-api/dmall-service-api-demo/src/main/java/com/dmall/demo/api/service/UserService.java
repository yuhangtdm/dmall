package com.dmall.demo.api.service;

import com.dmall.common.dto.*;
import com.dmall.demo.api.dto.user.request.ListUserRequestDTO;
import com.dmall.demo.api.dto.user.request.PageUserRequestDTO;
import com.dmall.demo.api.dto.user.response.UserResponseDTO;
import com.dmall.demo.api.dto.user.request.SaveUserRequestDTO;
import com.dmall.demo.api.dto.user.request.UpdateUserRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.validation.Valid;

/**
 * @description: 后台用户服务
 * @author: created by hang.yu on 2020-04-19 21:30:17
 */
@Api(tags = "后台用户服务")
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
    BaseResult<UserResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "后台用户列表")
    BaseResult<List<UserResponseDTO>> list(@RequestBody ListUserRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "后台用户分页")
    BaseResult<ResponsePage<UserResponseDTO>> page(@RequestBody PageUserRequestDTO requestDTO);

}

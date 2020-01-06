package com.dmall.sso.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.sso.api.dto.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @description: 后台登录服务
 * @author: created by hang.yu on 2020/1/6 23:07
 */
@Api(tags = "后台登录服务")
@Validated
@RequestMapping("/admin")
public interface AdminLoginService {

    /**
     * 登录
     */
    @PostMapping("/")
    @ApiOperation(value = "登录")
    BaseResult<String> login(@Valid @RequestBody AdminLoginRequestDTO requestDTO);

    @GetMapping("/checkToken")
    @ApiOperation(value = "校验token")
    BaseResult<UserDTO> checkToken(@NotBlank(message = "token不能为空") String token);

}

package com.dmall.sso.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.sso.api.dto.admin.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.admin.AdminLoginResponseDTO;
import com.dmall.sso.api.dto.portal.PortalLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.api.dto.portal.WeiBolLoginRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @description: PortalLoginService
 * @author: created by hang.yu on 2020/2/23 22:20
 */
@Api(tags = "前台商城登录服务")
@Validated
@RequestMapping("/portal")
public interface PortalLoginService {

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    BaseResult<PortalLoginResponseDTO> login(@Valid @RequestBody PortalLoginRequestDTO requestDTO);

    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    BaseResult<Void> logout(@NotBlank(message = "token不能为空") @RequestParam String token);

    @GetMapping("/checkToken")
    @ApiOperation(value = "校验token")
    BaseResult<PortalMemberDTO> checkToken(@NotBlank(message = "token不能为空") @RequestParam String token);

}

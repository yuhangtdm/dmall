package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.member.request.ForgetPasswordRequestDTO;
import com.dmall.mms.api.dto.member.request.RegisterMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.UpdatePasswordRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 会员服务
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Api(tags = "会员服务")
@RequestMapping("/member")
public interface MemberService {

    @GetMapping("/registerCode/{email}")
    @ApiOperation(value = "会员注册发送验证码")
    BaseResult<Void> registerCode(@PathVariable("email") String email);

    @GetMapping("/checkCode/{email}/{code}")
    @ApiOperation(value = "会员注册校验验证码")
    BaseResult<String> checkCode(@PathVariable("email") String email, @PathVariable("code") String code);

    @PostMapping("/register")
    @ApiOperation(value = "会员注册")
    BaseResult<Long> register(@RequestBody RegisterMemberRequestDTO requestDTO);

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    BaseResult<Long> updatePassword(@RequestBody UpdatePasswordRequestDTO requestDTO);

    @GetMapping("/updatePasswordCode/{email}")
    @ApiOperation(value = "忘记密码发送验证码")
    BaseResult<Long> updatePasswordCode(@PathVariable("email") String email);

    @PostMapping("/forgetPassword")
    @ApiOperation(value = "忘记密码")
    BaseResult<Long> forgetPassword(@RequestBody ForgetPasswordRequestDTO requestDTO);

}

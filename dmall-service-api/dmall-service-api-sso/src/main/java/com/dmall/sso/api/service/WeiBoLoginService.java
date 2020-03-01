package com.dmall.sso.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 微博登录服务
 * @author: created by hang.yu on 2020/3/1 16:57
 */
@Api(tags = "微博登录服务")
@Validated
@RequestMapping("/weibo")
public interface WeiBoLoginService {

    @GetMapping("/auth")
    @ApiOperation(value = "微博回调地址")
    BaseResult<PortalLoginResponseDTO> login(String code);

}

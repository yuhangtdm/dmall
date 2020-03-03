package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.mms.api.dto.member.request.WeiBoLoginRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @description: 第三方平台服务
 * @author: created by hang.yu on 2020/3/1 21:47
 */
@Api(tags = "第三方平台服务")
@RequestMapping("/thirdPartyPlatform")
public interface ThirdPartyPlatformService {

    @PostMapping("/weiBoLogin")
    @ApiOperation(value = "新浪微博登录")
    BaseResult<PortalMemberDTO> weiBoLogin(@Valid @RequestBody WeiBoLoginRequestDTO requestDTO);

}

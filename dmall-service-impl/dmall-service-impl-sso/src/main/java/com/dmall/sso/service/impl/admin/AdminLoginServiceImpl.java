package com.dmall.sso.service.impl.admin;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.sso.api.dto.admin.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.admin.AdminLoginResponseDTO;
import com.dmall.sso.api.enums.SsoErrorEnum;
import com.dmall.sso.api.service.AdminLoginService;
import com.dmall.sso.service.impl.SsoProperties;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description: 登录服务
 * @author: created by hang.yu on 2020/1/5 16:55
 */
@RestController
public class AdminLoginServiceImpl implements AdminLoginService {

    private static final String KEY_PREFIX = "admin_{}";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SsoProperties ssoProperties;

    /**
     * 登录
     */
    public BaseResult<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO requestDTO) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(requestDTO.getUserName(), requestDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        // 登录
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            throw new BusinessException(SsoErrorEnum.USER_NAME_INCORRECT);
        } catch (IncorrectCredentialsException e) {
            throw new BusinessException(SsoErrorEnum.PASSWORD_INCORRECT);
        } catch (LockedAccountException e) {
            throw new BusinessException(SsoErrorEnum.USER_INVALID);
        } catch (AuthenticationException e) {
            throw new BusinessException(SsoErrorEnum.AUTHENTICATION_FAILED);
        }

        AdminUserDTO adminUserDTO = (AdminUserDTO) subject.getPrincipal();
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(StrUtil.format(KEY_PREFIX, token), adminUserDTO, ssoProperties.getAdminTtlDay(), TimeUnit.DAYS);
        AdminLoginResponseDTO responseDTO = new AdminLoginResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUrl(StrUtil.isNotBlank(requestDTO.getUrl()) ? requestDTO.getUrl() : ssoProperties.getAdminSuccessUrl());
        return ResultUtil.success(responseDTO);
    }

    @Override
    public BaseResult<Void> logout(String token) {
        redisTemplate.delete(Lists.newArrayList(token));
        return ResultUtil.success();
    }

    /**
     * 校验token是否存在
     */
    public BaseResult<AdminUserDTO> checkToken(@RequestParam String token) {
        AdminUserDTO adminUserDTO = (AdminUserDTO) redisTemplate.opsForValue().get(token);
        if (adminUserDTO == null) {
            return ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN);
        }
        return ResultUtil.success(adminUserDTO);
    }

}

package com.dmall.sso.service.impl.admin;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.user.UserDTO;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import com.dmall.sso.api.dto.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.AdminLoginResponseDTO;
import com.dmall.sso.api.service.AdminLoginService;
import com.dmall.sso.service.impl.admin.dataobject.UserDO;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 登录服务
 * @author: created by hang.yu on 2020/1/5 16:55
 */
@RestController
public class LoginServiceImpl implements AdminLoginService {

    @Autowired
    private RedisTemplate<String, UserDTO> redisTemplate;

    @Autowired
    private DMallRedisProperties dMallRedisProperties;

    @Autowired
    private SsoAdminProperties ssoAdminProperties;

    /**
     * 登录
     *
     * @return token
     */
    public BaseResult<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO requestDTO) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(requestDTO.getUserName(), requestDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        // 登录
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            throw new BusinessException(LoginErrorEnum.USER_NAME_INCORRECT);
        } catch (IncorrectCredentialsException e) {
            throw new BusinessException(LoginErrorEnum.PASSWORD_INCORRECT);
        } catch (LockedAccountException e) {
            throw new BusinessException(LoginErrorEnum.USER_INVALID);
        } catch (AuthenticationException e) {
            throw new BusinessException(LoginErrorEnum.AUTHENTICATION_FAILED);
        }

        UserDTO userDTO  = (UserDTO) subject.getPrincipal();
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(token, userDTO,
                dMallRedisProperties.getTtl(), dMallRedisProperties.getTtlUnitEnum());
        AdminLoginResponseDTO responseDTO = new AdminLoginResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUrl(StrUtil.isNotBlank(requestDTO.getUrl()) ? requestDTO.getUrl() : ssoAdminProperties.getSuccessUrl());
        return ResultUtil.success(responseDTO);
    }

    @Override
    public BaseResult<Void> logout(String token) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redisTemplate.delete(Lists.newArrayList(token));
        return ResultUtil.success();
    }

    /**
     * 校验token是否存在
     */
    public BaseResult<UserDTO> checkToken(@RequestParam String token) {
        UserDTO userDTO = redisTemplate.opsForValue().get(token);
        if (userDTO == null) {
            return ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN);
        }
        return ResultUtil.success(userDTO);
    }


}

package com.dmall.sso.service.impl.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.cache.redis.mapcache.MapCacheUtil;
import com.dmall.sso.api.dto.admin.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.admin.AdminLoginResponseDTO;
import com.dmall.sso.api.dto.admin.UpdateLoginDTO;
import com.dmall.sso.api.enums.SsoErrorEnum;
import com.dmall.sso.api.service.AdminLoginService;
import com.dmall.sso.service.impl.SsoProperties;
import com.dmall.sso.service.impl.admin.mapper.UserMapper;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description: 登录服务
 * @author: created by hang.yu on 2020/1/5 16:55
 */
@RestController
public class AdminLoginServiceImpl implements AdminLoginService {

    private static final Integer REMEMBER_ME = 30;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SsoProperties ssoProperties;

    @Autowired
    private MapCacheUtil mapCacheUtil;

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     */
    public BaseResult<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO requestDTO) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(requestDTO.getPhone(), requestDTO.getPassword());
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

        if (requestDTO.getRememberMe() != null && requestDTO.getRememberMe()) {
            // 30天免登录
            adminUserDTO.setRememberMe(true);
            redisTemplate.opsForValue().set(token, adminUserDTO,
                    REMEMBER_ME, TimeUnit.DAYS);
        } else {
            adminUserDTO.setRememberMe(false);
            redisTemplate.opsForValue().set(token, adminUserDTO,
                    ssoProperties.getAdminTtlDay(), TimeUnit.DAYS);
        }
        // 将token和用户账号关联
        mapCacheUtil.put(adminUserDTO.getPhone(), token, token);

        AdminLoginResponseDTO responseDTO = new AdminLoginResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUrl(requestDTO.getUrl());
        return ResultUtil.success(responseDTO);
    }

    @Override
    public BaseResult<Void> logout(String token) {
        AdminUserDTO adminUserDTO = (AdminUserDTO) redisTemplate.opsForValue().get(token);
        if (adminUserDTO != null) {
            redisTemplate.delete(Lists.newArrayList(token));
            mapCacheUtil.delete(adminUserDTO.getPhone(), token);
        }
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

    @Override
    public BaseResult<Void> clearLogin(String phone) {
        List<Object> values = mapCacheUtil.values(phone);
        List<String> tokens = values.stream().map(Object::toString).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(tokens)) {
            redisTemplate.delete(tokens);
            mapCacheUtil.delete(phone, tokens.toArray(new String[0]));
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Void> updateLogin(UpdateLoginDTO requestDTO) {
        List<Object> values = mapCacheUtil.values(requestDTO.getPhone());
        List<String> tokens = values.stream().map(Object::toString).collect(Collectors.toList());
        for (String token : tokens) {
            AdminUserDTO adminUserDTO = (AdminUserDTO) redisTemplate.opsForValue().get(token);
            if (adminUserDTO != null) {
                adminUserDTO.setNickName(requestDTO.getNickName());
                adminUserDTO.setPhone(requestDTO.getPhone());
                adminUserDTO.setRealName(requestDTO.getRealName());
                adminUserDTO.setIcon(requestDTO.getIcon());
                adminUserDTO.setWarehouseId(requestDTO.getWarehouseId());
                Long expire = redisTemplate.getExpire(token, TimeUnit.SECONDS);
                if (expire != null) {
                    redisTemplate.opsForValue().set(token, adminUserDTO, expire, TimeUnit.SECONDS);
                }
            }
        }
        return ResultUtil.success();
    }

}

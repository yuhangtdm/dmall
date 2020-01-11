package com.dmall.sso.service.impl.admin;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.user.UserDTO;
import com.dmall.component.cache.redis.properties.DMallRedisProperties;
import com.dmall.common.util.ResultUtil;
import com.dmall.sso.service.impl.admin.dataobject.UserDO;
import com.dmall.sso.service.impl.admin.mapper.UserMapper;
import com.dmall.sso.api.dto.AdminLoginRequestDTO;
import com.dmall.sso.api.dto.AdminLoginResponseDTO;
import com.dmall.sso.api.service.AdminLoginService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @description: 登录服务
 * @author: created by hang.yu on 2020/1/5 16:55
 */
@RestController
public class LoginService implements AdminLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, UserDTO> redisTemplate;

    @Autowired
    private DMallRedisProperties dMallRedisProperties;

    @Value("${dmall.sso.index.url}")
    private String indexUrl;

    /**
     * 登录
     *
     * @return token
     */
    public BaseResult<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO requestDTO) {
        UserDO userDO = userMapper.selectOne(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUserName, requestDTO.getUserName()));
        if (userDO == null) {
            return ResultUtil.fail(LoginErrorEnum.USER_NAME_INCORRECT);
        }
        if (!requestDTO.getPassword().equals(userDO.getPassword())) {
            return ResultUtil.fail(LoginErrorEnum.PASSWORD_INCORRECT);
        }
        if (YNEnum.Y.getCode().equals(userDO.getIsDeleted())) {
            return ResultUtil.fail(LoginErrorEnum.USER_INVALID);
        }
        UserDTO userDTO = BeanUtil.copyProperties(userDO, UserDTO.class);
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(token, userDTO,
                dMallRedisProperties.getTtl(), dMallRedisProperties.getTtlUnitEnum());

        AdminLoginResponseDTO responseDTO = new AdminLoginResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUrl(StrUtil.isNotBlank(requestDTO.getUrl()) ? requestDTO.getUrl() : indexUrl);
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
    public BaseResult<UserDTO> checkToken(@RequestParam String token) {
        UserDTO userDTO = redisTemplate.opsForValue().get(token);
        if (userDTO == null) {
            return ResultUtil.fail(LoginErrorEnum.USER_NOT_LOGIN);
        }
        return ResultUtil.success(userDTO);
    }


}

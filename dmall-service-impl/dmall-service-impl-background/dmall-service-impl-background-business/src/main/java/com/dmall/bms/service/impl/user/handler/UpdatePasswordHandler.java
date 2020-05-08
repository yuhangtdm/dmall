package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.UpdatePasswordRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.feign.AdminLoginFeign;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.PasswordUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改密码处理器
 * @author: created by hang.yu on 2020/5/8 21:12
 */
@Component
public class UpdatePasswordHandler extends AbstractCommonHandler<UpdatePasswordRequestDTO, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminLoginFeign adminLoginFeign;

    @Override
    public BaseResult processor(UpdatePasswordRequestDTO requestDTO) {
        // id存在
        UserDO userDO = userMapper.selectById(requestDTO.getId());
        if (userDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NOT_EXIST);
        }
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (adminUserDTO.getId().equals(userDO.getId())) {
            return ResultUtil.fail(BackGroundErrorEnum.NO_AUTH_UPDATE);
        }
        userDO.setPassword(PasswordUtil.getPassword(userDO.getPhone(), requestDTO.getNewPassword()));
        // 清除登录信息
        adminLoginFeign.clearLogin(userDO.getPhone());
        return ResultUtil.success(requestDTO.getId());
    }
}

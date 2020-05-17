package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.UpdateUserRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.feign.AdminLoginFeign;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.service.support.DeliverWarehouseSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.dto.admin.UpdateLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class UpdateUserHandler extends AbstractCommonHandler<UpdateUserRequestDTO, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeliverWarehouseSupport deliverWarehouseSupport;

    @Autowired
    private AdminLoginFeign adminLoginFeign;

    @Override
    public BaseResult<Long> processor(UpdateUserRequestDTO requestDTO) {
        // id存在
        UserDO userDO = userMapper.selectById(requestDTO.getId());
        if (userDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NOT_EXIST);
        }
        if (requestDTO.getWarehouseId() != null) {
            deliverWarehouseSupport.validateWarehouse(requestDTO.getWarehouseId());
        }
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        if (!adminUserDTO.getId().equals(userDO.getId())) {
            return ResultUtil.fail(BackGroundErrorEnum.NO_AUTH_UPDATE);
        }
        userMapper.updateById(dtoConvertDo(requestDTO, UserDO.class));
        // 更新登录信息
        UpdateLoginDTO updateLogin = BeanUtil.copyProperties(requestDTO, UpdateLoginDTO.class);
        updateLogin.setPhone(userDO.getPhone());
        BaseResult<Void> updateLoginResult = adminLoginFeign.updateLogin(updateLogin);
        if (!updateLoginResult.getResult()) {
            return ResultUtil.fail(updateLoginResult.getCode(), updateLoginResult.getMsg());
        }
        return ResultUtil.success(userDO.getId());
    }

}
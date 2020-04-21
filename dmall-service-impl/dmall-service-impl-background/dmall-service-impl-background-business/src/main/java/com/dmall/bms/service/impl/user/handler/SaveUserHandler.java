package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.SaveUserRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.service.impl.support.DeliverWarehouseSupport;
import com.dmall.bms.service.impl.support.UserSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.PasswordUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class SaveUserHandler extends AbstractCommonHandler<SaveUserRequestDTO, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private DeliverWarehouseSupport deliverWarehouseSupport;

    @Override
    public BaseResult<Long> validate(SaveUserRequestDTO requestDTO) {
        // 用户名唯一
        UserDO userDO = userSupport.getByUserName(requestDTO.getUserName());
        if (userDO != null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NAME_EXIST);
        }
        if (requestDTO.getWarehouseId() != null) {
            deliverWarehouseSupport.validateWarehouse(requestDTO.getWarehouseId());
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveUserRequestDTO requestDTO) {
        UserDO userDO = dtoConvertDo(requestDTO, UserDO.class);
        userMapper.insert(userDO);
        return ResultUtil.success(userDO.getId());
    }

    @Override
    protected void customerConvertDo(UserDO result, SaveUserRequestDTO saveUserRequestDTO) {
        result.setPassword(PasswordUtil.getPassword(result.getUserName(), result.getPassword()));
        // 一期默认只有一个店铺
        result.setMerchantsId(1L);
    }
}

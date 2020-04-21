package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.UpdateUserRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.service.impl.support.DeliverWarehouseSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
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

    @Override
    public BaseResult<Long> validate(UpdateUserRequestDTO requestDTO) {
        // id存在
        UserDO userDO = userMapper.selectById(requestDTO.getId());
        if (userDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NOT_EXIST);
        }
        if (requestDTO.getWarehouseId() != null) {
            deliverWarehouseSupport.validateWarehouse(requestDTO.getWarehouseId());
        }

        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateUserRequestDTO requestDTO) {
        UserDO userDO = dtoConvertDo(requestDTO, UserDO.class);
        userMapper.updateById(userDO);
        return ResultUtil.success(userDO.getId());
    }

}
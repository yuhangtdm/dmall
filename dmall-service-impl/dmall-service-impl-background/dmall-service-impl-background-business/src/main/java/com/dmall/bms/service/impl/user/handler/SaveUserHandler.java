package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.SaveUserRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.service.support.UserSupport;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
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

    @Override
    public BaseResult<Long> validate(SaveUserRequestDTO requestDTO) {
        // 手机号唯一
        UserDO userDO = userSupport.getByPhone(requestDTO.getPhone());
        if (userDO != null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NAME_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveUserRequestDTO requestDTO) {
        UserDO userDO = dtoConvertDo(requestDTO, UserDO.class);
        userDO.setPassword(PasswordUtil.getPassword(requestDTO.getPhone(), Constants.PASSWORD));
        userMapper.insert(userDO);
        return ResultUtil.success(userDO.getId());
    }

    @Override
    protected void customerConvertDo(UserDO result, SaveUserRequestDTO saveUserRequestDTO) {
        // 一期默认只有一个店铺
        result.setStatus(YNEnum.Y.getCode());
        result.setMerchantsId(1L);
    }
}

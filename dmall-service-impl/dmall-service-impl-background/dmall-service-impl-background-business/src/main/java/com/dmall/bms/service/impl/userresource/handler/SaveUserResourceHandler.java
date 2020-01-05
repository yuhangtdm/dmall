package com.dmall.bms.service.impl.userresource.handler;

import com.dmall.bms.api.dto.userresource.request.SaveUserResourceRequestDTO;
import com.dmall.bms.service.impl.userresource.enums.UserResourceErrorEnum;
import com.dmall.bms.generator.dataobject.UserResourceDO;
import com.dmall.bms.generator.mapper.UserResourceMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class SaveUserResourceHandler extends AbstractCommonHandler<SaveUserResourceRequestDTO, UserResourceDO, Long> {

    @Autowired
    private UserResourceMapper userResourceMapper;

    @Override
    public BaseResult<Long> validate(SaveUserResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveUserResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

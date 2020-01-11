package com.dmall.bms.service.impl.userresource.handler;

import com.dmall.bms.api.dto.userresource.common.CommonUserResourceResponseDTO;
import com.dmall.bms.api.dto.userresource.request.PageUserResourceRequestDTO;
import com.dmall.bms.generator.dataobject.UserResourceDO;
import com.dmall.bms.generator.mapper.UserResourceMapper;
import com.dmall.common.dto.LayUiPage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class PageUserResourceHandler extends AbstractCommonHandler<PageUserResourceRequestDTO, UserResourceDO, CommonUserResourceResponseDTO> {

    @Autowired
    private UserResourceMapper userResourceMapper;

    @Override
    public BaseResult<LayUiPage<CommonUserResourceResponseDTO>> validate(PageUserResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonUserResourceResponseDTO>> processor(PageUserResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

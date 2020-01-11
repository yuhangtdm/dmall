package com.dmall.bms.service.impl.userresource;

import com.dmall.bms.api.dto.userresource.request.SaveUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.request.UpdateUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.request.ListUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.request.PageUserResourceRequestDTO;
import com.dmall.bms.api.dto.userresource.common.CommonUserResourceResponseDTO;
import com.dmall.bms.api.service.UserResourceService;
import com.dmall.bms.service.impl.userresource.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@RestController
public class UserResourceServiceImpl implements UserResourceService {

    @Autowired
    private SaveUserResourceHandler saveUserResourceHandler;

    @Autowired
    private DeleteUserResourceHandler deleteUserResourceHandler;

    @Autowired
    private UpdateUserResourceHandler updateUserResourceHandler;

    @Autowired
    private GetUserResourceHandler getUserResourceHandler;

    @Autowired
    private ListUserResourceHandler listUserResourceHandler;

    @Autowired
    private PageUserResourceHandler pageUserResourceHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveUserResourceRequestDTO requestDTO) {
        return saveUserResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteUserResourceHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserResourceRequestDTO requestDTO) {
        return updateUserResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonUserResourceResponseDTO> get(Long id) {
        return getUserResourceHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonUserResourceResponseDTO>> list(@RequestBody ListUserResourceRequestDTO requestDTO) {
        return listUserResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonUserResourceResponseDTO>> page(@RequestBody PageUserResourceRequestDTO requestDTO) {
        return pageUserResourceHandler.handler(requestDTO);
    }

}

package com.dmall.bms.service.impl.roleresource;

import com.dmall.bms.api.dto.roleresource.request.SaveRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.request.UpdateRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.request.ListRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.request.PageRoleResourceRequestDTO;
import com.dmall.bms.api.dto.roleresource.common.CommonRoleResourceResponseDTO;
import com.dmall.bms.api.service.RoleResourceService;
import com.dmall.bms.service.impl.roleresource.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台角色-资源服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@RestController
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private SaveRoleResourceHandler saveRoleResourceHandler;

    @Autowired
    private DeleteRoleResourceHandler deleteRoleResourceHandler;

    @Autowired
    private UpdateRoleResourceHandler updateRoleResourceHandler;

    @Autowired
    private GetRoleResourceHandler getRoleResourceHandler;

    @Autowired
    private ListRoleResourceHandler listRoleResourceHandler;

    @Autowired
    private PageRoleResourceHandler pageRoleResourceHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveRoleResourceRequestDTO requestDTO) {
        return saveRoleResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteRoleResourceHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateRoleResourceRequestDTO requestDTO) {
        return updateRoleResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonRoleResourceResponseDTO> get(Long id) {
        return getRoleResourceHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonRoleResourceResponseDTO>> list(@RequestBody ListRoleResourceRequestDTO requestDTO) {
        return listRoleResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonRoleResourceResponseDTO>> page(@RequestBody PageRoleResourceRequestDTO requestDTO) {
        return pageRoleResourceHandler.handler(requestDTO);
    }

}

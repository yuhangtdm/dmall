package com.dmall.bms.service.impl.role;

import com.dmall.bms.api.dto.role.request.*;
import com.dmall.bms.api.dto.role.response.RoleResponseDTO;
import com.dmall.bms.api.service.RoleService;
import com.dmall.bms.service.impl.role.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台角色服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@RestController
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SaveRoleHandler saveRoleHandler;

    @Autowired
    private DeleteRoleHandler deleteRoleHandler;

    @Autowired
    private UpdateRoleHandler updateRoleHandler;

    @Autowired
    private GetRoleHandler getRoleHandler;

    @Autowired
    private ListRoleHandler listRoleHandler;

    @Autowired
    private PageRoleHandler pageRoleHandler;

    @Autowired
    private RoleSetPermissionsHandler roleSetPermissionsHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveRoleRequestDTO requestDTO) {
        return saveRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteRoleHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateRoleRequestDTO requestDTO) {
        return updateRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<RoleResponseDTO> get(Long id) {
        return getRoleHandler.handler(id);
    }

    @Override
    public BaseResult<List<RoleResponseDTO>> list(@RequestBody ListRoleRequestDTO requestDTO) {
        return listRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<RoleResponseDTO>> page(@RequestBody PageRoleRequestDTO requestDTO) {
        return pageRoleHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> setPermissions(SetPermissionsRequestDTO requestDTO) {
        return roleSetPermissionsHandler.handler(requestDTO);
    }

}

package com.dmall.bms.service.impl.rolepermission;

import com.dmall.bms.api.dto.rolepermission.request.SaveRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.request.UpdateRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.request.ListRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.request.PageRolePermissionRequestDTO;
import com.dmall.bms.api.dto.rolepermission.common.CommonRolePermissionResponseDTO;
import com.dmall.bms.api.service.RolePermissionService;
import com.dmall.bms.service.impl.rolepermission.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 后台角色-资源服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@RestController
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private SaveRolePermissionHandler saveRolePermissionHandler;

    @Autowired
    private DeleteRolePermissionHandler deleteRolePermissionHandler;

    @Autowired
    private UpdateRolePermissionHandler updateRolePermissionHandler;

    @Autowired
    private GetRolePermissionHandler getRolePermissionHandler;

    @Autowired
    private ListRolePermissionHandler listRolePermissionHandler;

    @Autowired
    private PageRolePermissionHandler pageRolePermissionHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveRolePermissionRequestDTO requestDTO) {
        return saveRolePermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteRolePermissionHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateRolePermissionRequestDTO requestDTO) {
        return updateRolePermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonRolePermissionResponseDTO> get(Long id) {
        return getRolePermissionHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonRolePermissionResponseDTO>> list(@RequestBody ListRolePermissionRequestDTO requestDTO) {
        return listRolePermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonRolePermissionResponseDTO>> page(@RequestBody PageRolePermissionRequestDTO requestDTO) {
        return pageRolePermissionHandler.handler(requestDTO);
    }

}

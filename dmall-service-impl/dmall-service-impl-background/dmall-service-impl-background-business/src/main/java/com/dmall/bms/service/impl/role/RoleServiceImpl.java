package com.dmall.bms.service.impl.role;

import com.dmall.bms.api.dto.role.request.SaveRoleRequestDTO;
import com.dmall.bms.api.dto.role.request.UpdateRoleRequestDTO;
import com.dmall.bms.api.dto.role.request.ListRoleRequestDTO;
import com.dmall.bms.api.dto.role.request.PageRoleRequestDTO;
import com.dmall.bms.api.dto.role.common.CommonRoleResponseDTO;
import com.dmall.bms.api.service.RoleService;
import com.dmall.bms.service.impl.role.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台角色服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:37
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
    public BaseResult<CommonRoleResponseDTO> get(Long id) {
        return getRoleHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonRoleResponseDTO>> list(@RequestBody ListRoleRequestDTO requestDTO) {
        return listRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonRoleResponseDTO>> page(@RequestBody PageRoleRequestDTO requestDTO) {
        return pageRoleHandler.handler(requestDTO);
    }

}

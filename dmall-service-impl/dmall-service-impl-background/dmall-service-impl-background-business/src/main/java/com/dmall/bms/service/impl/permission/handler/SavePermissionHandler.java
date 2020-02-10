package com.dmall.bms.service.impl.permission.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.bms.api.dto.permission.enums.PermissionTypeEnum;
import com.dmall.bms.api.dto.permission.request.SavePermissionRequestDTO;
import com.dmall.bms.service.impl.permission.enums.PermissionErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.impl.support.PermissionSupport;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增权限处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class SavePermissionHandler extends AbstractCommonHandler<SavePermissionRequestDTO, PermissionDO, Long> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionSupport permissionSupport;

    @Override
    public BaseResult<Long> validate(SavePermissionRequestDTO requestDTO) {
        // 权限码唯一
        PermissionDO permissionDO = permissionSupport.getByCode(requestDTO.getCode());
        if (permissionDO != null){
            return ResultUtil.fail(PermissionErrorEnum.CODE_EXIST);
        }
        // 请求方式+uri唯一
        if (PermissionTypeEnum.URI.getCode().equals(requestDTO.getType())){
            if (StrUtil.isBlank(requestDTO.getUri()) || StrUtil.isBlank(requestDTO.getMethod())){
                return ResultUtil.fail(PermissionErrorEnum.URI_METHOD_ERROR);
            }
            PermissionDO byUriAndMethod = permissionSupport.getByUriAndMethod(requestDTO.getUri(), requestDTO.getMethod());
            if (byUriAndMethod != null){
                return ResultUtil.fail(PermissionErrorEnum.URI_METHOD_EXIST);
            }
        }
        // 目录id不为空时 必须存在 且必须是目录
        if (requestDTO.getParentId() != null){
            PermissionDO parentDO = permissionMapper.selectById(requestDTO.getParentId());
            if (parentDO == null){
                return ResultUtil.fail(PermissionErrorEnum.CATALOG_NOT_EXIST);
            }
            if (!PermissionTypeEnum.CATALOG.getCode().equals(parentDO.getType())){
                return ResultUtil.fail(PermissionErrorEnum.CATALOG_ERROR);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SavePermissionRequestDTO requestDTO) {
        PermissionDO permissionDO = dtoConvertDo(requestDTO, PermissionDO.class);
        permissionMapper.insert(permissionDO);
        return ResultUtil.success(permissionDO.getId());
    }

}

package com.dmall.bms.service.impl.permission.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.bms.api.dto.permission.enums.PermissionTypeEnum;
import com.dmall.bms.api.dto.permission.request.SavePermissionRequestDTO;
import com.dmall.bms.generator.dataobject.ServiceDO;
import com.dmall.bms.generator.mapper.ServiceMapper;
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

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public BaseResult<Long> validate(SavePermissionRequestDTO requestDTO) {
        // 服务必须存在
        ServiceDO serviceDO = serviceMapper.selectById(requestDTO.getServiceId());
        if (serviceDO == null){
            return ResultUtil.fail(PermissionErrorEnum.SERVICE_NOT_EXIST);
        }
        // 权限码唯一
        PermissionDO permissionDO = permissionSupport.getByCode(requestDTO.getCode());
        if (permissionDO != null){
            return ResultUtil.fail(PermissionErrorEnum.CODE_EXIST);
        }
        // 请求方式+uri唯一
        PermissionDO byUriAndMethod = permissionSupport.getByUriAndMethod(requestDTO.getUri(), requestDTO.getMethod());
        if (byUriAndMethod != null){
            return ResultUtil.fail(PermissionErrorEnum.URI_METHOD_EXIST);
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

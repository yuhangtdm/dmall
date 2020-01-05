package com.dmall.bms.service.impl.resource.handler;

import com.dmall.bms.api.dto.resource.request.SaveResourceRequestDTO;
import com.dmall.bms.service.impl.resource.enums.ResourceErrorEnum;
import com.dmall.bms.generator.dataobject.ResourceDO;
import com.dmall.bms.generator.mapper.ResourceMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增资源处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class SaveResourceHandler extends AbstractCommonHandler<SaveResourceRequestDTO, ResourceDO, Long> {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public BaseResult<Long> validate(SaveResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

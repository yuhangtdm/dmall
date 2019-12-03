package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class SaveAttributeHandler extends AbstractCommonHandler<SaveAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public BaseResult<Long> validate(SaveAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

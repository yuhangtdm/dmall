package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeTypeHandler extends AbstractCommonHandler<UpdateAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<Long> processor(UpdateAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = dtoConvertDo(requestDTO, AttributeTypeDO.class);
        attributeTypeMapper.updateById(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}

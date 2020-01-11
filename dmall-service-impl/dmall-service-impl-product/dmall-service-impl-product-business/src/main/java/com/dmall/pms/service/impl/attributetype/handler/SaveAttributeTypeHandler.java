package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class SaveAttributeTypeHandler extends AbstractCommonHandler<SaveAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Override
    public BaseResult<Long> processor(SaveAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = dtoConvertDo(requestDTO, AttributeTypeDO.class);
        attributeTypeCacheService.insert(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}

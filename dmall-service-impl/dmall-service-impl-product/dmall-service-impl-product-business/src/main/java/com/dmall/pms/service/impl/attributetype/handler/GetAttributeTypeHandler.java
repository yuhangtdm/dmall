package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.support.CategorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性类别处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, AttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategorySupport categorySupport;

    @Override
    public BaseResult<AttributeTypeResponseDTO> processor(Long id) {
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(id);
        if (attributeTypeDO == null) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(attributeTypeDO, AttributeTypeResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(AttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
        }
    }
}

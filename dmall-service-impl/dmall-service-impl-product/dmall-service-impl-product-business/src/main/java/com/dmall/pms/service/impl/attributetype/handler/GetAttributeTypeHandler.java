package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.api.dto.attributetype.enums.AttributeTypeEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.service.impl.category.support.CategorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, CommonAttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategorySupport categorySupport;

    @Override
    public BaseResult<CommonAttributeTypeResponseDTO> processor(Long id) {
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(id);
        if (attributeTypeDO == null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(attributeTypeDO, CommonAttributeTypeResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(CommonAttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
//            result.setType(EnumUtil.getKeyValueEnum(AttributeTypeEnum.class, doo.getType()));
        }
    }
}

package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.service.support.CategorySupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性类别处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, AttributeTypeResponseDTO> {

    @Autowired
    private CategorySupport categorySupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<AttributeTypeResponseDTO> processor(Long id) {
        AttributeTypeDO attributeTypeDO = pmsValidate.validateAttributeType(id);
        return ResultUtil.success(doConvertDto(attributeTypeDO, AttributeTypeResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(AttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
        }
    }
}

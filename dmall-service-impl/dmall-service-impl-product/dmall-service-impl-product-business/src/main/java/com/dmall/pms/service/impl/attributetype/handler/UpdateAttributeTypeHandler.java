package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性类别处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeTypeHandler extends AbstractCommonHandler<UpdateAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> processor(UpdateAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = pmsValidate.validateAttributeType(requestDTO.getId());
        attributeTypeDO.setShowName(requestDTO.getShowName());
        if (attributeTypeDO.getCategoryId() != null) {
            CategoryDO categoryDO = categoryCacheService.selectById(attributeTypeDO.getCategoryId());
            attributeTypeDO.setName(StrUtil.format("{}_{}", categoryDO.getName(), requestDTO.getShowName()));
        }
        attributeTypeCacheService.updateById(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}

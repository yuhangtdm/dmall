package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.api.enums.AttributeTypeErrorEnum;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeTypeHandler extends AbstractCommonHandler<UpdateAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<Long> processor(UpdateAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = attributeTypeMapper.selectById(requestDTO.getId());
        if (attributeTypeDO == null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
        attributeTypeDO.setShowName(requestDTO.getShowName());
        if (attributeTypeDO.getCategoryId() != null) {
            CategoryDO categoryDO = categoryCacheService.selectById(attributeTypeDO.getCategoryId());
            attributeTypeDO.setName(StrUtil.format("{}_{}", categoryDO.getName(), requestDTO.getShowName()));
        }
        attributeTypeCacheService.updateById(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}

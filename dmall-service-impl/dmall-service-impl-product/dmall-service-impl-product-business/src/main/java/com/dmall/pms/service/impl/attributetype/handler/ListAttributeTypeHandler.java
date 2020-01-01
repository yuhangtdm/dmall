package com.dmall.pms.service.impl.attributetype.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.service.impl.attributetype.wrapper.LambdaQueryWrapperBuilder;
import com.dmall.pms.service.impl.support.CategorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 属性分类列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class ListAttributeTypeHandler extends AbstractCommonHandler<ListAttributeTypeRequestDTO, AttributeTypeDO, CommonAttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategorySupport categorySupport;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult validate(ListAttributeTypeRequestDTO requestDTO) {
        CategoryDO categoryDO = categoryMapper.selectById(requestDTO.getCategoryId());
        if (categoryDO == null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonAttributeTypeResponseDTO>> processor(ListAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> attributeTypeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getCategoryId(), requestDTO.getShowName())) {
            attributeTypeDOS = attributeTypeCacheService.selectAll();
        } else {
            LambdaQueryWrapper<AttributeTypeDO> queryWrapper = LambdaQueryWrapperBuilder
                    .queryWrapper(requestDTO.getCategoryId(), requestDTO.getShowName());
            attributeTypeDOS = attributeTypeMapper.selectList(queryWrapper);
        }
        List<CommonAttributeTypeResponseDTO> list = attributeTypeDOS.stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, CommonAttributeTypeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

    @Override
    protected void customerConvertDto(CommonAttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
        }
    }
}

package com.dmall.pms.service.impl.attributetype.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.wrapper.LambdaQueryWrapperBuilder;
import com.dmall.pms.service.support.CategorySupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 属性类别列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class ListAttributeTypeHandler extends AbstractCommonHandler<ListAttributeTypeRequestDTO, AttributeTypeDO, AttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategorySupport categorySupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult validate(ListAttributeTypeRequestDTO requestDTO) {
        CategoryDO categoryDO = pmsValidate.validateCategory(requestDTO.getCategoryId());
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<AttributeTypeResponseDTO>> processor(ListAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> attributeTypeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getCategoryId(), requestDTO.getShowName())) {
            attributeTypeDOS = attributeTypeCacheService.selectAll();
        } else {
            LambdaQueryWrapper<AttributeTypeDO> queryWrapper = LambdaQueryWrapperBuilder
                    .queryWrapper(requestDTO.getCategoryId(), requestDTO.getShowName());
            attributeTypeDOS = attributeTypeMapper.selectList(queryWrapper);
        }
        List<AttributeTypeResponseDTO> list = attributeTypeDOS.stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, AttributeTypeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

    @Override
    protected void customerConvertDto(AttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
        }
    }
}

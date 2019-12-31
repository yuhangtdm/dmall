package com.dmall.pms.service.impl.attributetype.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.request.PageAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.PageAttributeTypeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.wrapper.LambdaQueryWrapperBuilder;
import com.dmall.pms.service.impl.support.CategorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 属性分类分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class PageAttributeTypeHandler extends AbstractCommonHandler<PageAttributeTypeRequestDTO, AttributeTypeDO, PageAttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private CategorySupport categorySupport;

    @Override
    public BaseResult<LayuiPage<PageAttributeTypeResponseDTO>> processor(PageAttributeTypeRequestDTO requestDTO) {
        LambdaQueryWrapper<AttributeTypeDO> queryWrapper = LambdaQueryWrapperBuilder
                .queryWrapper(requestDTO.getCategoryId(), requestDTO.getShowName());
        IPage<AttributeTypeDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page = attributeTypeMapper.selectPage(page, queryWrapper);
        List<PageAttributeTypeResponseDTO> record = page.getRecords().stream()
                .map(doo -> doConvertDto(doo, PageAttributeTypeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new LayuiPage(page.getTotal(), record));
    }

    @Override
    protected void customerConvertDto(PageAttributeTypeResponseDTO result, AttributeTypeDO doo) {
        if (doo.getCategoryId() != null) {
            result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
        }
    }

}

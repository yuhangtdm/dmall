package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.request.setattribute.AttributeIdsDTO;
import com.dmall.pms.api.dto.category.request.setattribute.SetAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.generator.service.ICategoryAttributeService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import com.dmall.pms.service.impl.support.AttributeTypeSupport;
import com.dmall.pms.service.impl.support.CategorySupport;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 设置属性处理器
 * @author: created by hang.yu on 2019/12/25 23:03
 */
@Component
public class SetAttributeHandler extends AbstractCommonHandler<SetAttributeRequestDTO, CategoryAttributeDO, Void> {

    @Autowired
    private CategorySupport categorySupport;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private ICategoryAttributeService iCategoryAttributeService;

    @Autowired
    private AttributeTypeSupport attributeTypeSupport;

    @Override
    public BaseResult validate(SetAttributeRequestDTO requestDTO) {
        // 属性列表不能重复
        Set<Long> attributeIds = requestDTO.getAttributes().stream()
                .map(AttributeIdsDTO::getAttributeId)
                .collect(Collectors.toSet());
        if (requestDTO.getAttributes().size() != attributeIds.size()) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTE_ID_REPEATED);
        }
        // 属性列表的id都必须存在
        List<CategoryDO> categoryDOList = categoryMapper.selectBatchIds(attributeIds);
        if (categoryDOList.size() != attributeIds.size()) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTE_ID_INVALID);
        }
        return categorySupport.validate(requestDTO.getCategoryId());
    }

    @Override
    public BaseResult processor(SetAttributeRequestDTO requestDTO) {
        // 先删除后插入
        attributeTypeSupport.deleteByCategoryId(requestDTO.getCategoryId());
        CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
        List<CategoryAttributeDO> list = Lists.newArrayList();
        for (AttributeIdsDTO attributeIdDTO : requestDTO.getAttributes()) {
            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
            categoryAttributeDO.setCategoryId(requestDTO.getCategoryId());
            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
            categoryAttributeDO.setAttributeId(attributeIdDTO.getAttributeId());
            categoryAttributeDO.setCanScreen(attributeIdDTO.getCanScreen());
            list.add(categoryAttributeDO);
        }
        iCategoryAttributeService.saveBatch(list);
        return ResultUtil.success();
    }
}

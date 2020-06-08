package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.request.setattribute.AttributeIdsDTO;
import com.dmall.pms.api.dto.category.request.setattribute.SetAttributeRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import com.dmall.pms.service.validate.PmsValidate;
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
    private AttributeMapper categoryMapper;

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult validate(SetAttributeRequestDTO requestDTO) {
        // 属性列表不能重复
        Set<Long> attributeIds = requestDTO.getAttributes().stream()
            .map(AttributeIdsDTO::getAttributeId)
            .collect(Collectors.toSet());
        if (requestDTO.getAttributes().size() != attributeIds.size()) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_ID_REPEATED);
        }
        // 属性列表的id都必须存在
        List<AttributeDO> attributes = categoryMapper.selectBatchIds(attributeIds);
        if (attributes.size() != attributeIds.size()) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_ID_INVALID);
        }
        return pmsValidate.validateThreeLevelCategory(requestDTO.getCategoryId());
    }

    @Override
    public BaseResult processor(SetAttributeRequestDTO requestDTO) {
        List<CategoryAttributeDO> listByCategoryId = categoryAttributeSupport
            .listByCategoryId(requestDTO.getCategoryId());
        List<AttributeIdsDTO> attributes = requestDTO.getAttributes();
        List<Long> attributeIds = attributes.stream().map(AttributeIdsDTO::getAttributeId).collect(Collectors.toList());
        // 角色列表为空 只是新增
        if (CollUtil.isEmpty(listByCategoryId)) {
            insert(requestDTO.getCategoryId(), attributes);
        } else {
            // 先删后增
            List<Long> oldAttributeIds = listByCategoryId.stream().map(CategoryAttributeDO::getAttributeId)
                .collect(Collectors.toList());
            // 新增的集合
            List<AttributeIdsDTO> insertAttributes = attributes.stream()
                .filter(attributeIdsDTO -> !oldAttributeIds.contains(attributeIdsDTO.getAttributeId()))
                .collect(Collectors.toList());
            // 删除的集合
            List<Long> deleteAttributeIds = oldAttributeIds.stream()
                .filter(attributeId -> !attributeIds.contains(attributeId))
                .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deleteAttributeIds)) {
                categoryAttributeSupport.delete(deleteAttributeIds, requestDTO.getCategoryId());
            }
            if (CollUtil.isNotEmpty(insertAttributes)) {
                insert(requestDTO.getCategoryId(), insertAttributes);
            }
            // 修改是否可筛选
            attributes.stream().filter(attributeIdsDTO -> oldAttributeIds.contains(attributeIdsDTO.getAttributeId()))
                .forEach(attributeIdsDTO -> {
                    CategoryAttributeDO categoryAttributeDO = categoryAttributeSupport
                        .get(attributeIdsDTO.getAttributeId(), requestDTO.getCategoryId());
                    categoryAttributeDO.setCanScreen(attributeIdsDTO.getCanScreen());
                    categoryAttributeMapper.updateById(categoryAttributeDO);
                });
        }
        return ResultUtil.success();
    }

    /**
     * 新增记录
     */
    private void insert(Long categoryId, List<AttributeIdsDTO> attributes) {
        for (AttributeIdsDTO attribute : attributes) {
            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO()
                .setAttributeId(attribute.getAttributeId())
                .setCanScreen(attribute.getCanScreen())
                .setCategoryId(categoryId);
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
            categoryAttributeMapper.insert(categoryAttributeDO);
        }
    }
}

package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.request.SetAttributeCategoryRequestDTO;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.api.enums.TypeEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: SetAttributeCategoryHandler
 * @author: created by hang.yu on 2020/5/26 22:25
 */
@Component
@Deprecated
public class SetAttributeCategoryHandler
    extends AbstractCommonHandler<SetAttributeCategoryRequestDTO, CategoryAttributeDO, Void> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Override
    public BaseResult validate(SetAttributeCategoryRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getAttributeId());
        if (attributeDO == null) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_ID_INVALID);
        }
        List<CategoryDO> categoryDOS = categoryMapper.selectBatchIds(requestDTO.getCategoryIds());
        if (categoryDOS.size() != requestDTO.getCategoryIds().size()) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 必须是三级分类
        Optional<Boolean> any = categoryDOS.stream()
            .map(categoryDO -> !LevelEnum.THREE.getCode().equals(categoryDO.getLevel()))
            .findAny();
        if (any.isPresent()) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_INVALID);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(SetAttributeCategoryRequestDTO requestDTO) {
        List<CategoryAttributeDO> listByAttributeId = categoryAttributeSupport
            .listByAttributeId(requestDTO.getAttributeId());
        Set<Long> categoryIds = requestDTO.getCategoryIds();
        // 角色列表为空 只是新增
        if (CollUtil.isEmpty(listByAttributeId)) {
            insert(requestDTO, categoryIds);
        } else {
            // 先删后增
            List<Long> oldCategoryIds = listByAttributeId.stream().map(CategoryAttributeDO::getCategoryId)
                .collect(Collectors.toList());
            // 新增的集合
            List<Long> insertCategoryIds = categoryIds.stream()
                .filter(menuId -> !oldCategoryIds.contains(menuId))
                .collect(Collectors.toList());
            // 删除的集合
            List<Long> deleteCategoryIds = oldCategoryIds.stream()
                .filter(menuId -> !categoryIds.contains(menuId))
                .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deleteCategoryIds)) {
                categoryAttributeSupport.delete(requestDTO.getAttributeId(), deleteCategoryIds);
            }
            if (CollUtil.isNotEmpty(insertCategoryIds)) {
                insert(requestDTO, insertCategoryIds);
            }
        }
        return ResultUtil.success();
    }

    /**
     * 新增记录
     */
    private void insert(SetAttributeCategoryRequestDTO requestDTO, Collection<Long> categoryIds) {
        for (Long categoryId : categoryIds) {
            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO()
                .setAttributeId(requestDTO.getAttributeId())
                .setCanScreen(requestDTO.getCanScreen())
                .setCategoryId(categoryId);
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
            categoryAttributeMapper.insert(categoryAttributeDO);
        }
    }

}

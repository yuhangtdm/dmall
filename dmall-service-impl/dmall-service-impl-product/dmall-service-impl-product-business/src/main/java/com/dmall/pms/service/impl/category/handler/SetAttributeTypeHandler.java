package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.cache.redis.constants.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.MapCacheUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.request.setattributetype.AttributeTypeIdsDTO;
import com.dmall.pms.api.dto.category.request.setattributetype.SetAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.service.IAttributeService;
import com.dmall.pms.generator.service.IAttributeTypeService;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import com.dmall.pms.service.impl.category.support.CategorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 设置分类处理器
 * @author: created by hang.yu on 2019/12/5 21:19
 */
@Component
public class SetAttributeTypeHandler extends AbstractCommonHandler<SetAttributeTypeRequestDTO, CategoryDO, Void> {

    @Autowired
    private IAttributeTypeService iAttributeTypeService;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private IAttributeService iAttributeService;

    @Autowired
    private CategorySupport categorySupport;

    @Autowired(required = false)
    private MapCacheUtil mapCacheUtil;

    @Override
    public BaseResult validate(SetAttributeTypeRequestDTO requestDTO) {
        // 属性分类id不能为空
        if (CollUtil.isEmpty(requestDTO.getAttributeTypeIds())) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTE_TYPE_ID_EMPTY);
        }
        Set<Long> attributeTypeIds = requestDTO.getAttributeTypeIds().stream().map(AttributeTypeIdsDTO::getAttributeTypeId).collect(Collectors.toSet());
        // 属性分类id不能有重复
        if (attributeTypeIds.size() != requestDTO.getAttributeTypeIds().size()) {
            return ResultUtil.fail(CategoryErrorEnum.BRAND_IDS_INVALID);
        }
        Collection<AttributeTypeDO> attributeTypeDOS = iAttributeTypeService.listByIds(attributeTypeIds);
        // 属性分类id要存在
        if (attributeTypeDOS.size() != attributeTypeIds.size()) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTE_TYPE_ID_INVALID);
        }

        return categorySupport.validate(requestDTO.getCategoryId());
    }

    @Override
    public BaseResult processor(SetAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> list = iAttributeTypeService.list(Wrappers.<AttributeTypeDO>lambdaQuery()
                .eq(AttributeTypeDO::getCategoryId, requestDTO.getCategoryId()));
        if (CollUtil.isNotEmpty(list)) {
            List<Long> deleteAttributeTypeIds = list.stream()
                    .map(AttributeTypeDO::getId)
                    .filter(attributeTypeId -> !requestDTO.getAttributeTypeIds().contains(attributeTypeId))
                    .collect(Collectors.toList());
            deleteAttributeTypeIds.forEach(attributeTypeId -> {
                LambdaUpdateWrapper<AttributeTypeDO> updateWrapper = Wrappers.<AttributeTypeDO>update().lambda()
                        .eq(AttributeTypeDO::getId, attributeTypeId)
                        .set(AttributeTypeDO::getCategoryId, null)
                        .set(AttributeTypeDO::getCascadeCategoryId, null);
                attributeTypeMapper.update(null, updateWrapper);
                mapCacheUtil.put(mapCacheUtil.getkey(CacheNameConstants.ATTRIBUTE_TYPE, AttributeTypeCacheService.class),
                        String.valueOf(attributeTypeId), iAttributeTypeService.getById(attributeTypeId));
                iAttributeService.update(Wrappers.<AttributeDO>update().lambda()
                        .eq(AttributeDO::getAttributeTypeId, attributeTypeId)
                        .set(AttributeDO::getCategoryId, null)
                        .set(AttributeDO::getCascadeCategoryId, null)
                );
            });
        }
        requestDTO.getAttributeTypeIds().forEach(attributeTypeIdsDTO -> {
            AttributeTypeDO attributeTypeDO = new AttributeTypeDO();
            attributeTypeDO.setId(attributeTypeIdsDTO.getAttributeTypeId());
            attributeTypeDO.setSort(attributeTypeIdsDTO.getSort());
            attributeTypeDO.setCategoryId(requestDTO.getCategoryId());
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            attributeTypeDO.setCascadeCategoryId(categoryDO.getPath());
            // 更新属性分类以及缓存
            iAttributeTypeService.updateById(attributeTypeDO);
            mapCacheUtil.put(mapCacheUtil.getkey(CacheNameConstants.ATTRIBUTE_TYPE, AttributeTypeCacheService.class),
                    String.valueOf(attributeTypeDO.getId()), iAttributeTypeService.getById(attributeTypeDO.getId()));
            // 批量更新属性以及缓存
            iAttributeService.update(Wrappers.<AttributeDO>update().lambda()
                    .eq(AttributeDO::getAttributeTypeId, attributeTypeIdsDTO.getAttributeTypeId())
                    .set(AttributeDO::getCategoryId, attributeTypeDO.getCategoryId())
                    .set(AttributeDO::getCascadeCategoryId, attributeTypeDO.getCascadeCategoryId())
            );
            List<AttributeDO> attributeDOS = iAttributeService.list(Wrappers.<AttributeDO>lambdaQuery()
                    .eq(AttributeDO::getAttributeTypeId, attributeTypeIdsDTO.getAttributeTypeId()));
            Map<String, Object> attributeCache = attributeDOS.stream().collect(Collectors.toMap(attributeDO ->
                    String.valueOf(attributeDO.getId()), attributeDO -> attributeDO));
            mapCacheUtil.batchPut(mapCacheUtil.getkey(CacheNameConstants.ATTRIBUTE, AttributeCacheService.class), attributeCache);
        });

        return ResultUtil.success();
    }
}

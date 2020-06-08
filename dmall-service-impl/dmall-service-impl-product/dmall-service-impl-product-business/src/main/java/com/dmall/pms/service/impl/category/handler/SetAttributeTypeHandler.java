package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.cache.redis.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.MapCacheUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.request.setattributetype.AttributeTypeIdsDTO;
import com.dmall.pms.api.dto.category.request.setattributetype.SetAttributeTypeRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.service.IAttributeTypeService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.AttributeTypeSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 设置分类处理器
 * @author: created by hang.yu on 2019/12/5 21:19
 */
@Component
@Deprecated
public class SetAttributeTypeHandler extends AbstractCommonHandler<SetAttributeTypeRequestDTO, CategoryDO, Void> {

    @Autowired
    private IAttributeTypeService iAttributeTypeService;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private AttributeTypeSupport attributeTypeSupport;

    @Autowired
    private MapCacheUtil mapCacheUtil;

    @Override
    public BaseResult validate(SetAttributeTypeRequestDTO requestDTO) {
        Set<Long> attributeTypeIds = requestDTO.getAttributeTypes().stream()
            .map(AttributeTypeIdsDTO::getAttributeTypeId).collect(Collectors.toSet());
        // 属性类别id不能有重复
        if (attributeTypeIds.size() != requestDTO.getAttributeTypes().size()) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_ID_REPEATED);
        }
        // 属性类别id必须存在
        List<AttributeTypeDO> attributeTypes = attributeTypeMapper.selectBatchIds(attributeTypeIds);
        if (attributeTypes.size() != attributeTypeIds.size()) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_ID_INVALID);
        }
        return pmsValidate.validateThreeLevelCategory(requestDTO.getCategoryId());
    }

    @Override
    public BaseResult processor(SetAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> list = attributeTypeSupport.listByCategoryId(requestDTO.getCategoryId());
        List<Long> attributeTypeIds =
            requestDTO.getAttributeTypes().stream().map(AttributeTypeIdsDTO::getAttributeTypeId)
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(list)) {
            List<Long> deleteAttributeTypeIds = list.stream()
                .map(AttributeTypeDO::getId)
                .filter(attributeTypeId -> !attributeTypeIds.contains(attributeTypeId))
                .collect(Collectors.toList());
            // 修改被删除的属性类别以及缓存
            deleteAttributeTypeIds.forEach(attributeTypeId -> {
                LambdaUpdateWrapper<AttributeTypeDO> updateWrapper = Wrappers.<AttributeTypeDO>update().lambda()
                    .eq(AttributeTypeDO::getId, attributeTypeId)
                    .set(AttributeTypeDO::getCategoryId, null)
                    .set(AttributeTypeDO::getCascadeCategoryId, null);
                attributeTypeMapper.update(null, updateWrapper);
                mapCacheUtil.put(
                    mapCacheUtil.getKey(CacheNameConstants.ATTRIBUTE_TYPE, AttributeTypeCacheService.class),
                    String.valueOf(attributeTypeId), iAttributeTypeService.getById(attributeTypeId));
            });
        }

        // 全量的更新属性类别以及缓存
        requestDTO.getAttributeTypes().forEach(attributeTypeIdDTO -> {
            AttributeTypeDO attributeTypeDO =
                attributeTypeCacheService.selectById(attributeTypeIdDTO.getAttributeTypeId());
            attributeTypeDO.setSort(attributeTypeIdDTO.getSort());
            attributeTypeDO.setCategoryId(requestDTO.getCategoryId());
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            attributeTypeDO.setCascadeCategoryId(categoryDO.getPath());
            attributeTypeDO.setName(StrUtil.format("{}_{}", categoryDO.getName(), attributeTypeDO.getShowName()));
            iAttributeTypeService.updateById(attributeTypeDO);
            mapCacheUtil.put(mapCacheUtil.getKey(CacheNameConstants.ATTRIBUTE_TYPE, AttributeTypeCacheService.class),
                String.valueOf(attributeTypeDO.getId()), iAttributeTypeService.getById(attributeTypeDO.getId()));
        });

        return ResultUtil.success();
    }
}

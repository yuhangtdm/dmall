package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.setattributetype.AttributeTypeIdsDTO;
import com.dmall.pms.api.dto.category.request.setattributetype.SetAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.category.request.setbrand.BrandIdsDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.service.IAttributeCategoryService;
import com.dmall.pms.generator.service.IAttributeService;
import com.dmall.pms.generator.service.IAttributeTypeService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
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


    @Override
    public BaseResult validate(SetAttributeTypeRequestDTO requestDTO) {
        // 属性分类id不能为空
        if (CollUtil.isEmpty(requestDTO.getAttributeTypeIds())) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTETYPE_ID_EMPTY);
        }
        Set<Long> attributeTypeIds = requestDTO.getAttributeTypeIds().stream().map(AttributeTypeIdsDTO::getAttributeTypeId).collect(Collectors.toSet());
        // 属性分类id不能有重复
        if (attributeTypeIds.size() != requestDTO.getAttributeTypeIds().size()) {
            return ResultUtil.fail(CategoryErrorEnum.BRAND_IDS_INVALID);
        }
        Collection<AttributeTypeDO> attributeTypeDOS = iAttributeTypeService.listByIds(attributeTypeIds);
        // 属性分类id要存在
        if (attributeTypeDOS.size() != attributeTypeIds.size()) {
            return ResultUtil.fail(CategoryErrorEnum.ATTRIBUTETYPE_ID_INVALID);
        }
        CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
        // 分类id必须存在
        if (categoryDO == null) {
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 分类级别必须是3级
        if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(CategoryErrorEnum.PARENT_LEVEL_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(SetAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> list = iAttributeTypeService.list(Wrappers.<AttributeTypeDO>lambdaQuery()
                .eq(AttributeTypeDO::getCategoryId, requestDTO.getCategoryId()));
        if (CollUtil.isNotEmpty(list)){
            List<Long> deleteAttributeTypeIds = list.stream()
                    .map(AttributeTypeDO::getId)
                    .filter(attributeTypeId -> !requestDTO.getAttributeTypeIds().contains(attributeTypeId))
                    .collect(Collectors.toList());
            deleteAttributeTypeIds.forEach(attributeTypeId -> {
                LambdaUpdateWrapper<AttributeTypeDO> updateWrapper = Wrappers.<AttributeTypeDO>update().lambda()
                        .eq(AttributeTypeDO::getId,attributeTypeId)
                        .set(AttributeTypeDO::getCategoryId, null)
                        .set(AttributeTypeDO::getCascadeCategoryId, null);
                attributeTypeMapper.update(null, updateWrapper);
            });
        }
        requestDTO.getAttributeTypeIds().forEach(attributeTypeIdsDTO ->{
            AttributeTypeDO attributeTypeDO = new AttributeTypeDO();
            attributeTypeDO.setId(attributeTypeIdsDTO.getAttributeTypeId());
            attributeTypeDO.setSort(attributeTypeIdsDTO.getSort());
            attributeTypeDO.setCategoryId(requestDTO.getCategoryId());
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            attributeTypeDO.setCascadeCategoryId(categoryDO.getPath());
            // 更新属性分类
            iAttributeTypeService.updateById(attributeTypeDO);
            // 批量更新属性
            iAttributeService.update(Wrappers.<AttributeDO>update().lambda()
                    .eq(AttributeDO::getAttributeTypeId, attributeTypeIdsDTO.getAttributeTypeId())
                    .set(AttributeDO::getCategoryId, attributeTypeDO.getCategoryId())
                    .set(AttributeDO::getCascadeCategoryId, attributeTypeDO.getCascadeCategoryId())
            );
        });

        return ResultUtil.success();
    }
}

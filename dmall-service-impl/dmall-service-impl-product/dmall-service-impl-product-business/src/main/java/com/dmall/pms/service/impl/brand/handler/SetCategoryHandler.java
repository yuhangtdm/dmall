package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.CheckedDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.CategoryBrandSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 设置分类处理器
 * @author: created by hang.yu on 2019/12/4 22:44
 */
@Component
public class SetCategoryHandler extends AbstractCommonHandler<CheckedDTO, CategoryDO, Void> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryBrandSupport categoryBrandSupport;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult validate(CheckedDTO requestDTO) {

        BrandDO brandDO = brandMapper.selectById(requestDTO.getId());
        if (brandDO == null) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_ID_INVALID);
        }
        List<CategoryDO> categoryList = categoryMapper.selectBatchIds(requestDTO.getRelateIds());
        if (categoryList.size() != requestDTO.getRelateIds().size()) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(CheckedDTO requestDTO) {
        List<CategoryBrandDO> listByBrandId = categoryBrandSupport.listByBrandId(requestDTO.getId());
        Set<Long> categoryIds = requestDTO.getRelateIds();
        // 角色列表为空 只是新增
        if (CollUtil.isEmpty(listByBrandId)) {
            insert(requestDTO, categoryIds);
        } else {
            // 先删后增
            List<Long> oldCategoryIds = listByBrandId.stream().map(CategoryBrandDO::getCategoryId)
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
                categoryBrandSupport.delete(requestDTO.getId(), deleteCategoryIds);
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
    private void insert(CheckedDTO requestDTO, Collection<Long> categoryIds) {
        for (Long categoryId : categoryIds) {
            CategoryBrandDO categoryBrandDO = new CategoryBrandDO()
                    .setBrandId(requestDTO.getId())
                    .setCategoryId(categoryId);
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            categoryBrandDO.setCascadeCategoryId(categoryDO.getPath());
            categoryBrandMapper.insert(categoryBrandDO);
        }
    }
}

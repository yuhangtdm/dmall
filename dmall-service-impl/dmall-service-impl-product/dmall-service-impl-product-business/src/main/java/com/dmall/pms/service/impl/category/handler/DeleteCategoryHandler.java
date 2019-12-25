package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class DeleteCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategorySkuMapper categorySkuMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 分类不存在
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (categoryDO == null) {
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 删除1、2级分类必须没有子分类
        if (categoryDO.getLevel() != LevelEnum.THREE.getCode()){
            List<CategoryDO> categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getParentId, id));
            if (CollUtil.isNotEmpty(categoryDOList)){
                return ResultUtil.fail(CategoryErrorEnum.CONTAINS_SUB_CATEGORY_ERROR);
            }
        }
        // 如果分类下有sku 则不可删除
        List<CategorySkuDO> productDOS = categorySkuMapper.selectList(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(productDOS)) {
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_SKU_ERROR);
        }
        // 如果分类下有属性分类 则不可删除
        List<AttributeTypeDO> attributeTypeDOS = attributeTypeMapper.selectList(Wrappers.<AttributeTypeDO>lambdaQuery().eq(AttributeTypeDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(attributeTypeDOS)) {
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_ATTRIBUTE_TYPE_ERROR);
        }
        // 分类下有属性不可删除
        List<CategoryAttributeDO> categoryAttributeDOS = categoryAttributeMapper.selectList(Wrappers.<CategoryAttributeDO>lambdaQuery().eq(CategoryAttributeDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(categoryAttributeDOS)) {
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_ATTRIBUTE_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除商品分类以及和品牌的对应关系
        categoryCacheService.deleteById(id);
        categoryBrandMapper.delete(Wrappers.<CategoryBrandDO>lambdaQuery().eq(CategoryBrandDO::getCategoryId, id));
        return ResultUtil.success(id);
    }

}

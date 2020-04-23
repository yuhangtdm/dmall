package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.dataobject.CategorySkuDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.*;
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
    private CategorySupport categorySupport;

    @Autowired
    private CategoryBrandSupport categoryBrandSupport;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategoryProductSupport categoryProductSupport;

    @Autowired
    private CategorySkuSupport categorySkuSupport;

    @Autowired
    private AttributeTypeSupport attributeTypeSupport;

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 分类不存在
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (categoryDO == null) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 删除1、2级分类必须没有子分类
        if (!categoryDO.getLevel().equals(LevelEnum.THREE.getCode())) {
            List<CategoryDO> categoryDOList = categorySupport.listByParentId(id);
            if (CollUtil.isNotEmpty(categoryDOList)) {
                return ResultUtil.fail(PmsErrorEnum.CONTAINS_SUB_CATEGORY_ERROR);
            }
        }
        // 如果分类下有商品,则不可删除
        List<CategoryProductDO> categoryProductList = categoryProductSupport.listByCategoryId(id);
        if (CollUtil.isNotEmpty(categoryProductList)) {
            return ResultUtil.fail(PmsErrorEnum.CONTAINS_PRODUCT_ERROR);
        }
        // 如果分类下有sku 则不可删除
        List<CategorySkuDO> productDOS = categorySkuSupport.listByCategoryId(id);
        if (CollUtil.isNotEmpty(productDOS)) {
            return ResultUtil.fail(PmsErrorEnum.CONTAINS_SKU_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除商品分类以及和品牌的对应关系
        categoryCacheService.deleteById(id);
        categoryBrandSupport.deleteByCategoryId(id);
        attributeTypeSupport.deleteByCategoryId(id);
        categoryAttributeSupport.deleteByCategoryId(id);
        return ResultUtil.success(id);
    }

}

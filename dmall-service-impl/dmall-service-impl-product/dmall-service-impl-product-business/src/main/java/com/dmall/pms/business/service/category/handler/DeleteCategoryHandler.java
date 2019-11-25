package com.dmall.pms.business.service.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.business.service.category.enums.CategoryErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: DeleteCategoryHandler
 * @author: created by hang.yu on 2019/11/24 14:44
 */
@Component
public class DeleteCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult validate(Long id) {
        // 分类不存在时
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (categoryDO == null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 如果分类下有商品 则不可删除
        List<ProductDO> productDOS = productMapper.selectList(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(productDOS)){
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_PRODUCT_ERROR);
        }
        // 如果分类下有品牌 则不可删除
        List<CategoryBrandDO> categoryBrandDOS = categoryBrandMapper.selectList(Wrappers.<CategoryBrandDO>lambdaQuery().eq(CategoryBrandDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(categoryBrandDOS)){
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_BRAND_ERROR);
        }
        // 如果分类下有属性分类 则不可删除
        List<AttributeTypeDO> attributeTypeDOS = attributeTypeMapper.selectList(Wrappers.<AttributeTypeDO>lambdaQuery().eq(AttributeTypeDO::getCategoryId, id));
        if (CollUtil.isNotEmpty(attributeTypeDOS)){
            return ResultUtil.fail(CategoryErrorEnum.CONTAINS_ATTRIBUTE_TYPE_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(Long id) {
        if (categoryMapper.deleteById(id) != 1){
            return ResultUtil.fail(CategoryErrorEnum.DELETE_CATEGORY_ERROR);
        }
        return ResultUtil.success(id);
    }
}

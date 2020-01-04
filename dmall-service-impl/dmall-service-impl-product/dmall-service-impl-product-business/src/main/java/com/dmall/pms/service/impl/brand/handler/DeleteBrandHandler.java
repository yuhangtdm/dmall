package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.brand.enums.BrandErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除品牌处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class DeleteBrandHandler extends AbstractCommonHandler<Long, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 品牌必须存在
        BrandDO brandDO = brandMapper.selectById(id);
        if (brandDO == null) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NOT_EXIST);
        }
        // 如果品牌下有商品 则不能删除
        List<ProductDO> productDOS = productMapper.selectList(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getBrandId, id));
        if (CollUtil.isNotEmpty(productDOS)) {
            return ResultUtil.fail(BrandErrorEnum.CONTAINS_PRODUCT_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除品牌以及和商品分类的对应关系
        brandCacheService.deleteById(id);
        categoryBrandMapper.delete(Wrappers.<CategoryBrandDO>lambdaQuery().eq(CategoryBrandDO::getBrandId, id));
        return ResultUtil.success(id);
    }

}

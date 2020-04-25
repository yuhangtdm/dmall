package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.support.CategoryBrandSupport;
import com.dmall.pms.service.support.ProductSupport;
import com.dmall.pms.service.validate.PmsValidate;
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
    private BrandCacheService brandCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private ProductSupport productSupport;

    @Autowired
    private CategoryBrandSupport categoryBrandSupport;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 品牌必须存在
        pmsValidate.validateBrand(id);
        // 如果品牌下有商品 则不能删除
        List<ProductDO> productDOS = productSupport.listByBrandId(id);
        if (CollUtil.isNotEmpty(productDOS)) {
            return ResultUtil.fail(PmsErrorEnum.CONTAINS_PRODUCT_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除品牌以及和商品分类的对应关系
        brandCacheService.deleteById(id);
        categoryBrandSupport.deleteByBrandId(id);
        return ResultUtil.success(id);
    }

}

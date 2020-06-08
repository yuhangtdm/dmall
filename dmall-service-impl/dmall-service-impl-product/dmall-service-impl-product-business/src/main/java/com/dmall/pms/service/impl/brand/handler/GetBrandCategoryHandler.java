package com.dmall.pms.service.impl.brand.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.support.CategoryBrandSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: GetBrandCategoryHandler
 * @author: created by hang.yu on 2020/5/19 21:55
 */
@Component
public class GetBrandCategoryHandler extends AbstractCommonHandler<Long, CategoryBrandDO, List<String>> {

    @Autowired
    private CategoryBrandSupport categoryBrandSupport;

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<List<String>> processor(Long brandId) {
        // 品牌必须存在
        BrandDO brandDO = brandCacheService.selectById(brandId);
        if (brandDO == null) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_NOT_EXIST);
        }
        List<String> collect = categoryBrandSupport.listByBrandId(brandId)
            .stream().map(categoryBrandDO -> String.valueOf(categoryBrandDO.getCategoryId()))
            .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}

package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.setbrand.BrandIdsDTO;
import com.dmall.pms.api.dto.category.request.setbrand.SetBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.service.IBrandService;
import com.dmall.pms.generator.service.ICategoryBrandService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/4 22:44
 */
@Component
public class SetBrandHandler extends AbstractCommonHandler<SetBrandRequestDTO, CategoryDO, Void> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private IBrandService iBrandService;

    @Autowired
    private ICategoryBrandService iCategoryBrandService;

    @Override
    public BaseResult validate(SetBrandRequestDTO requestDTO) {
        // 品牌id不能为空
        if (CollUtil.isEmpty(requestDTO.getBrandIds())) {
            return ResultUtil.fail(CategoryErrorEnum.BRAND_ID_EMPTY);
        }
        Set<Long> brandIds = requestDTO.getBrandIds().stream().map(BrandIdsDTO::getBrandId).collect(Collectors.toSet());
        // 品牌id不能有重复
        if (brandIds.size() != requestDTO.getBrandIds().size()) {
            return ResultUtil.fail(CategoryErrorEnum.BRAND_IDS_INVALID);
        }
        Collection<BrandDO> brandDOS = iBrandService.listByIds(brandIds);
        // 品牌id都要存在
        if (brandDOS.size() != requestDTO.getBrandIds().size()) {
            return ResultUtil.fail(CategoryErrorEnum.BRAND_ID_INVALID);
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
    public BaseResult processor(SetBrandRequestDTO requestDTO) {
        // 先删除 后批量插入
        iCategoryBrandService.remove(Wrappers.<CategoryBrandDO>lambdaQuery()
                .eq(CategoryBrandDO::getCategoryId, requestDTO.getCategoryId()));
        List<CategoryBrandDO> insertList = requestDTO.getBrandIds().stream().map(brand -> {
            CategoryBrandDO categoryBrandDO = new CategoryBrandDO();
            categoryBrandDO.setCategoryId(requestDTO.getCategoryId());
            categoryBrandDO.setBrandId(brand.getBrandId());
            categoryBrandDO.setSort(brand.getSort());
            return categoryBrandDO;
        }).collect(Collectors.toList());
        iCategoryBrandService.saveBatch(insertList);
        return ResultUtil.success();
    }
}

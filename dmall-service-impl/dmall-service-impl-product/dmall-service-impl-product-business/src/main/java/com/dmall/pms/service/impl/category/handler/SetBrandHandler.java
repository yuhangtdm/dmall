package com.dmall.pms.service.impl.category.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.request.setbrand.BrandIdsDTO;
import com.dmall.pms.api.dto.category.request.setbrand.SetBrandRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.generator.service.ICategoryBrandService;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 设置品牌处理器
 * @author: created by hang.yu on 2019/12/4 22:44
 */
@Component
@Deprecated
public class SetBrandHandler extends AbstractCommonHandler<SetBrandRequestDTO, CategoryDO, Void> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ICategoryBrandService iCategoryBrandService;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult validate(SetBrandRequestDTO requestDTO) {
        Set<Long> brandIds = requestDTO.getBrandIds().stream()
            .map(BrandIdsDTO::getBrandId).collect(Collectors.toSet());
        // 品牌id不能有重复
        if (brandIds.size() != requestDTO.getBrandIds().size()) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_IDS_REPEATED);
        }
        List<BrandDO> brands = brandMapper.selectBatchIds(brandIds);
        // 品牌id必须存在
        if (brands.size() != requestDTO.getBrandIds().size()) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_ID_INVALID);
        }
        return pmsValidate.validateThreeLevelCategory(requestDTO.getCategoryId());
    }

    @Override
    public BaseResult processor(SetBrandRequestDTO requestDTO) {
        // 先删除 后批量插入
        iCategoryBrandService.remove(Wrappers.<CategoryBrandDO>lambdaQuery()
            .eq(CategoryBrandDO::getCategoryId, requestDTO.getCategoryId()));
        List<CategoryBrandDO> insertList = requestDTO.getBrandIds().stream()
            .map(brand -> {
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

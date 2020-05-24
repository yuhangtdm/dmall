package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.UpdateBrandRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改品牌处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class UpdateBrandHandler extends AbstractCommonHandler<UpdateBrandRequestDTO, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(UpdateBrandRequestDTO requestDTO) {
        // 品牌必须存在
        pmsValidate.validateBrand(requestDTO.getId());
        // 品牌名称唯一
        BrandDO nameBrand = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, requestDTO.getName()));
        if (nameBrand != null && ObjectUtil.notEqual(nameBrand.getId(), requestDTO.getId())) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_NAME_UNIQUE);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateBrandRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        brandCacheService.updateById(brandDO);
        return ResultUtil.success(brandDO.getId());
    }

}

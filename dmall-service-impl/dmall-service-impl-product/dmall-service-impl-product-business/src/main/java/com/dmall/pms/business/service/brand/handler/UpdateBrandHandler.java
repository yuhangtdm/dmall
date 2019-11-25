package com.dmall.pms.business.service.brand.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.UpdateBrandRequestDTO;
import com.dmall.pms.business.service.brand.enums.BrandErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 更新品牌处理器
 * @author: created by hang.yu on 2019/11/23 9:53
 */
@Component
public class UpdateBrandHandler extends AbstractCommonHandler<UpdateBrandRequestDTO, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult validate(UpdateBrandRequestDTO requestDTO) {
        BrandDO brandDO = brandMapper.selectById(requestDTO.getId());
        if (brandDO == null) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NOT_EXIST);
        }

        BrandDO nameBrand = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, requestDTO.getName()));
        if (nameBrand != null && ObjectUtil.notEqual(nameBrand.getId(), requestDTO.getId())) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NAME_UNIQUE);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(UpdateBrandRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        if (brandMapper.updateById(brandDO) != 1) {
            return ResultUtil.fail(BrandErrorEnum.SAVE_BRAND_ERROR);
        }
        return ResultUtil.success(brandDO.getId());
    }

}

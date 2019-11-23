package com.dmall.pms.business.service.brand.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.update.UpdateBrandCommonRequestDTO;
import com.dmall.pms.business.service.brand.enums.BrandErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

/**
 * @description: 更新品牌处理器
 * @author: created by hang.yu on 2019/11/23 9:53
 */
@Component
public class UpdateBrandHandler extends AbstractCommonHandler<UpdateBrandCommonRequestDTO, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult validate(UpdateBrandCommonRequestDTO requestDTO) {
        BrandDO brandDO = brandMapper.selectById(requestDTO.getId());
        if (brandDO == null){
            return ResultUtil.fail(BrandErrorEnum.BRAND_NOT_EXIST);
        }

        BrandDO nameBrand = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, requestDTO.getName()));
        if (nameBrand != null && !Objects.equals(nameBrand.getId(), requestDTO.getId())) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NAME_UNIQUE);
        }
        return ResultUtil.success();
    }

    @Override
    protected BaseResult processor(UpdateBrandCommonRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        if (brandMapper.updateById(brandDO) != 1) {
            return ResultUtil.fail(BrandErrorEnum.SAVE_BRAND_ERROR);
        }
        return  ResultUtil.success(brandDO.getId());
    }

}

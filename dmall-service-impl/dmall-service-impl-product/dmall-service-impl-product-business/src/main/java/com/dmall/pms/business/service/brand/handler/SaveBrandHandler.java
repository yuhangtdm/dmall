package com.dmall.pms.business.service.brand.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.SaveBrandRequestDTO;
import com.dmall.pms.business.service.brand.enums.BrandErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 保存品牌处理器
 * @author: created by hang.yu on 2019/11/19 23:27
 */
@Component
public class SaveBrandHandler extends AbstractCommonHandler<SaveBrandRequestDTO, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult validate(SaveBrandRequestDTO requestDTO) {
        BrandDO brandDO = brandMapper.selectOne(Wrappers.<BrandDO>query().eq("name", requestDTO.getName()));
        if (brandDO != null) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NAME_UNIQUE);
        }
        return ResultUtil.success();
    }

    /**
     * 新增品牌
     */
    @Override
    public BaseResult processor(SaveBrandRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        if (brandMapper.insert(brandDO) != 1) {
            return ResultUtil.fail(BrandErrorEnum.SAVE_BRAND_ERROR);
        }
        return ResultUtil.success(brandDO.getId());
    }

}

package com.dmall.pms.business.service.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.business.service.brand.enums.BrandErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除品牌处理器
 * @author: created by hang.yu on 2019/11/23 13:47
 */
@Component
public class DeleteBrandHandler extends AbstractCommonHandler<Long, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public BaseResult validate(Long id) {
        BrandDO brandDO = brandMapper.selectById(id);
        if (brandDO == null){
            return ResultUtil.fail(BrandErrorEnum.BRAND_DELETED);
        }
        return ResultUtil.success();
    }

    @Override
    protected BaseResult processor(Long id) {
        if (brandMapper.deleteById(id) == 1){
            return ResultUtil.success(id);
        }

        return ResultUtil.fail(BrandErrorEnum.DELETE_BRAND_ERROR);
    }
}

package com.dmall.pms.service.impl.sku.handler;

import com.dmall.pms.api.dto.sku.request.UpdateSkuRequestDTO;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class UpdateSkuHandler extends AbstractCommonHandler<UpdateSkuRequestDTO, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<Long> validate(UpdateSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

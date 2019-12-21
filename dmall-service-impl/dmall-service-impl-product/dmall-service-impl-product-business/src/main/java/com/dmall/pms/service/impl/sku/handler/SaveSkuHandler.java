package com.dmall.pms.service.impl.sku.handler;

import com.dmall.pms.api.dto.sku.request.SaveSkuRequestDTO;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增sku处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Component
public class SaveSkuHandler extends AbstractCommonHandler<SaveSkuRequestDTO, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<Long> validate(SaveSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

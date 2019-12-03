package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.api.dto.skuattribute.request.SaveSkuAttributeRequestDTO;
import com.dmall.pms.service.impl.skuattribute.enums.SkuAttributeErrorEnum;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增sku属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveSkuAttributeHandler extends AbstractCommonHandler<SaveSkuAttributeRequestDTO, SkuAttributeDO, Long> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<Long> validate(SaveSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

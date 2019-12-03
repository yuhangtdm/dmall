package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.service.impl.skuattribute.enums.SkuAttributeErrorEnum;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除sku属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class DeleteSkuAttributeHandler extends AbstractCommonHandler<Long, SkuAttributeDO, Long> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}

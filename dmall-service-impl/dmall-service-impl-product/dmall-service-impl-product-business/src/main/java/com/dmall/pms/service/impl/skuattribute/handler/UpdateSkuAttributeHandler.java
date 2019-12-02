package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.api.dto.skuattribute.request.UpdateSkuAttributeRequestDTO;
import com.dmall.pms.service.impl.skuattribute.enums.SkuAttributeErrorEnum;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改sku属性处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class UpdateSkuAttributeHandler extends AbstractCommonHandler<UpdateSkuAttributeRequestDTO, SkuAttributeDO, Long> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<Long> validate(UpdateSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

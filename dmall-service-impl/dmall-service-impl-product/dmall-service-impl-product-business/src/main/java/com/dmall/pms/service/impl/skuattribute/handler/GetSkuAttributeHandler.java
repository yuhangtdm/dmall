package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeResponseDTO;
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
 * @description: 查询sku属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetSkuAttributeHandler extends AbstractCommonHandler<Long, SkuAttributeDO, CommonSkuAttributeResponseDTO> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<CommonSkuAttributeResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonSkuAttributeResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}

package com.dmall.pms.service.impl.productattribute.handler;

import com.dmall.pms.api.dto.productattribute.request.SaveProductAttributeRequestDTO;
import com.dmall.pms.service.impl.productattribute.enums.ProductAttributeErrorEnum;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性值处理器
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Component
public class SaveProductAttributeHandler extends AbstractCommonHandler<SaveProductAttributeRequestDTO, ProductAttributeDO, Long> {

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Override
    public BaseResult<Long> validate(SaveProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

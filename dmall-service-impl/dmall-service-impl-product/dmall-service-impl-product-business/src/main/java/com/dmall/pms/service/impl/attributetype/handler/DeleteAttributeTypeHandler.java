package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.ProductAttributeValueDO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.support.ProductAttributeValueSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除属性类别处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class DeleteAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 属性类别必须存在
        pmsValidate.validateAttributeType(id);
        // 商品属性值不存在该属性类别
        List<ProductAttributeValueDO> attributeValueDOS = productAttributeValueSupport.listByAttributeTypeId(id);
        if (CollUtil.isNotEmpty(attributeValueDOS)) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_HAS_PRODUCT);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        attributeTypeCacheService.deleteById(id);
        return ResultUtil.success(id);
    }

}

package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class DeleteAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        List<SkuAttributeDO> skuAttributeDOS = skuAttributeMapper.selectList(Wrappers.<SkuAttributeDO>lambdaQuery()
                .eq(SkuAttributeDO::getAttributeTypeId, id));
        // 属性分类下不可有sku
        if (CollUtil.isNotEmpty(skuAttributeDOS)) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_HAS_SKU);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        attributeTypeCacheService.deleteById(id);
        return ResultUtil.success(id);
    }

}

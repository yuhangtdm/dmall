package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.ProductAttributeValueDO;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import com.dmall.pms.generator.mapper.ProductAttributeValueMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class DeleteAttributeHandler extends AbstractCommonHandler<Long, AttributeDO, Long> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        List<ProductAttributeValueDO> productAttributeValueDOS = productAttributeValueMapper
                .selectList(Wrappers.<ProductAttributeValueDO>lambdaQuery().eq(ProductAttributeValueDO::getAttributeId, id));

        // 属性下有商品 则不能删除
        if (CollUtil.isNotEmpty(productAttributeValueDOS)) {
            return ResultUtil.fail(AttributeErrorEnum.CONTAINS_PRODUCT);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除属性 以及 分类-属性
        attributeCacheService.deleteById(id);
        categoryAttributeMapper.delete(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getAttributeId, id));
        return ResultUtil.success(id);
    }

}

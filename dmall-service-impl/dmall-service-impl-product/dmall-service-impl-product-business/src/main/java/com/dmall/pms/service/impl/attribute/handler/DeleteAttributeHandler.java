package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ProductAttributeMapper productAttributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        List<ProductAttributeDO> productAttributeDOS = productAttributeMapper.selectList(Wrappers.<ProductAttributeDO>lambdaQuery()
                .eq(ProductAttributeDO::getAttributeId, id));
        // 属性下有商品则不可删除
        if (CollUtil.isNotEmpty(productAttributeDOS)){
            return ResultUtil.fail(AttributeErrorEnum.DELETE_ATTRIBUTE_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        attributeCacheService.deleteById(id);
        return ResultUtil.success(id);
    }

}

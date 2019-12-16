package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class DeleteProductHandler extends AbstractCommonHandler<Long, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        // 校验商品是否已删除
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        // 删除商品
        productMapper.deleteById(id);
        // 删除商品属性
        productAttributeMapper.delete(Wrappers.<ProductAttributeDO>lambdaQuery()
                .eq(ProductAttributeDO::getProductId, id));
        // todo 删除sku
        return ResultUtil.success(id);
    }

}

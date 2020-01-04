package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.service.impl.support.ProductAttributeValueSupport;
import com.dmall.pms.service.impl.support.SkuSupport;
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
    private SkuSupport skuSupport;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

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
        // 删除商品属性值
        productAttributeValueSupport.deleteByProductId(id);
        // 删除sku相关数据
        skuSupport.deleteSkuByProductId(id);
        return ResultUtil.success(id);
    }

}

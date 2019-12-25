package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.*;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.service.impl.sku.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 删除商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class DeleteProductHandler extends AbstractCommonHandler<Long, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private SkuMediaMapper skuMediaMapper;

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

        List<SkuDO> skuDOS = skuSupport.selectByProductId(id);
        skuDOS.forEach(skuDO -> {
            skuExtMapper.delete(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuDO.getId()));
            skuMediaMapper.delete(Wrappers.<SkuMediaDO>lambdaQuery().eq(SkuMediaDO::getSkuId, skuDO.getId()));
        });
        skuMapper.delete(Wrappers.<SkuDO>lambdaQuery()
                .eq(SkuDO::getProductId, id));
        return ResultUtil.success(id);
    }

}

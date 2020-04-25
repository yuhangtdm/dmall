package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.support.ProductAttributeValueSupport;
import com.dmall.pms.service.support.SkuSupport;
import com.dmall.pms.service.validate.PmsValidate;
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

    @Autowired
    private PmsValidate pmsValidate;


    @Override
    public BaseResult<Long> validate(Long id) {
        // 校验商品是否已删除
        pmsValidate.validateProduct(id);
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

package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.CategorySkuSupport;
import com.dmall.pms.service.support.SkuAttributeValueSupport;
import com.dmall.pms.service.support.SkuExtSupport;
import com.dmall.pms.service.support.SkuMediaSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除sku处理器
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Component
public class DeleteSkuHandler extends AbstractCommonHandler<Long, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private SkuMediaSupport skuMediaSupport;

    @Autowired
    private CategorySkuSupport categorySkuSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(Long id) {
        pmsValidate.validateSku(id);
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long skuId) {
        // 删除sku
        skuMapper.deleteById(skuId);
        // 删除sku扩展信息
        skuExtSupport.deleteBySkuId(skuId);
        // 删除sku图片信息
        skuMediaSupport.deleteBySkuId(skuId);
        // 删除sku属性信息
        skuAttributeValueSupport.deleteBySkuId(skuId);
        // 删除分类-sku信息
        categorySkuSupport.deleteBySkuId(skuId);
        return ResultUtil.success(skuId);
    }
}

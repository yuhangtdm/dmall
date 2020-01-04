package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.*;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
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
    private SkuExtMapper skuExtMapper;

    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Autowired
    private CategorySkuMapper categorySkuMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        SkuDO sku = skuMapper.selectById(id);
        if (sku == null) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long skuId) {
        // 删除sku
        skuMapper.deleteById(skuId);
        // 删除sku扩展信息
        skuExtMapper.delete(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuId));
        // 删除sku图片信息
        skuMediaMapper.delete(Wrappers.<SkuMediaDO>lambdaQuery().eq(SkuMediaDO::getSkuId, skuId));
        // 删除sku属性信息
        skuAttributeValueMapper.delete(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getSkuId, skuId));
        // 删除分类-sku信息
        categorySkuMapper.delete(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getSkuId, skuId));
        return ResultUtil.success(skuId);
    }

}

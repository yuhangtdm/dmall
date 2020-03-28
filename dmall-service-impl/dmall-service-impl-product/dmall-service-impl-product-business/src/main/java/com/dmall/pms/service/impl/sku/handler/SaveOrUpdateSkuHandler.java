package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuRequestDTO;
import com.dmall.pms.api.enums.SkuErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class SaveOrUpdateSkuHandler extends AbstractCommonHandler<SaveSkuRequestDTO, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<Long> validate(SaveSkuRequestDTO requestDTO) {
        // 商品必须存在
        ProductDO productDO = productMapper.selectById(requestDTO.getProductId());
        if (productDO == null) {
            return ResultUtil.fail(SkuErrorEnum.PRODUCT_NOT_EXISTS);
        }
        // 名称必须唯一
        SkuDO skuDO = skuMapper.selectOne(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getName, requestDTO.getName()));
        if (requestDTO.getId() == null) {
            if (skuDO != null) {
                return ResultUtil.fail(SkuErrorEnum.SKU_NAME_EXISTS);
            }
        } else {
            SkuDO sku = skuMapper.selectById(requestDTO.getId());
            if (sku == null) {
                return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
            }
            if (skuDO != null && !skuDO.getId().equals(requestDTO.getId())) {
                return ResultUtil.fail(SkuErrorEnum.SKU_NAME_EXISTS);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveSkuRequestDTO requestDTO) {
        SkuDO skuDO = dtoConvertDo(requestDTO, SkuDO.class);
        if (requestDTO.getId() == null) {
            skuDO.setId(IdGeneratorUtil.snowflakeId());
            skuDO.setAuditStatus(SkuAuditStatusEnum.NOT_AUDIT.getCode());
            skuDO.setPublishStatus(YNEnum.N.getCode());
            skuMapper.insert(skuDO);
        } else {
            skuMapper.updateById(skuDO);
        }
        return ResultUtil.success(skuDO.getId());
    }

}

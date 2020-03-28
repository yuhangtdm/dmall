package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.LockSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.LockStockRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/28 23:01
 */
@Component
public class LockStockHandler extends AbstractCommonHandler<LockStockRequestDTO, SkuDO, Void> {
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public BaseResult processor(LockStockRequestDTO requestDTO) {
        for (LockSkuRequestDTO sku : requestDTO.getSku()) {
            SkuDO skuDO = skuMapper.selectById(sku.getSkuId());
            if (skuDO == null){

            }
            skuDO.setLockStock(skuDO.getStock() + sku.getNumber());
            skuMapper.updateById(skuDO);
        }
        return null;
    }
}

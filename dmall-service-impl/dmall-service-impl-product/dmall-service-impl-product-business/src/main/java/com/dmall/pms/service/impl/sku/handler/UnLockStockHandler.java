package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.service.support.SkuStockSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 释放库存处理器
 * @author: created by hang.yu on 2020/3/28 23:01
 */
@Component
public class UnLockStockHandler extends AbstractCommonHandler<StockRequestDTO, SkuDO, Void> {

    @Autowired
    private SkuStockSupport skuStockSupport;

    @Override
    public BaseResult processor(StockRequestDTO requestDTO) {
        skuStockSupport.unLockStock(requestDTO.getSku());
        return ResultUtil.success();
    }
}

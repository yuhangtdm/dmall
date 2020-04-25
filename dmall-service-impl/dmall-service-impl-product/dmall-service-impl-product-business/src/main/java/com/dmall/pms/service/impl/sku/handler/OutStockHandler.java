package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 出库处理器
 * @author: created by hang.yu on 2020/4/4 14:26
 */
@Component
public class OutStockHandler extends AbstractCommonHandler<StockRequestDTO, SkuDO, Void> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult processor(StockRequestDTO requestDTO){
         List<SkuStockRequestDTO> skuList = requestDTO.getSku();
        for (SkuStockRequestDTO skuRequestDTO : skuList) {
            SkuDO skuDO = pmsValidate.validateSku(skuRequestDTO.getSkuId());
            skuDO.setStock(skuDO.getStock() - skuRequestDTO.getNumber());
            skuMapper.updateById(skuDO);
        }
        return ResultUtil.success();
    }
}

package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuAttributeValueDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.service.impl.support.SkuAttributeValueSupport;
import com.dmall.pms.service.impl.support.SkuExtSupport;
import com.dmall.pms.service.impl.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: SaveSkuAttributeHandler
 * @author: created by hang.yu on 2019/12/16 16:43
 */
@Component
public class SaveSkuAttributeHandler extends AbstractCommonHandler<SaveSkuAttributeRequestDTO, SkuAttributeValueDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private SkuExtSupport skuExtSupport;


    @Override
    public BaseResult<Long> validate(SaveSkuAttributeRequestDTO requestDTO) {
        return skuSupport.validate(requestDTO.getProductId(), requestDTO.getSkuId());
    }

    @Override
    public BaseResult<Long> processor(SaveSkuAttributeRequestDTO requestDTO) {
        Long productId = requestDTO.getProductId();
        Long skuId = requestDTO.getSkuId();
        List<Long> productAttributeValueList = requestDTO.getProductAttributeValueList();
        // 设置sku属性值
        skuAttributeValueSupport.setSkuAttributeValue(productId, skuId, productAttributeValueList);
        // 设置sku扩展表
        skuExtSupport.setSkuExt(productId, skuId, productAttributeValueList, null, null);
        return ResultUtil.success(skuId);
    }


}

package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.SkuAttributeValueDO;
import com.dmall.pms.service.support.SkuAttributeValueSupport;
import com.dmall.pms.service.support.SkuExtSupport;
import com.dmall.pms.service.support.SkuSupport;
import com.dmall.pms.service.validate.PmsValidate;
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
    private SkuSupport skuSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(SaveSkuAttributeRequestDTO requestDTO) {
        return pmsValidate.skuValidate(requestDTO.getProductId(), requestDTO.getSkuId());
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

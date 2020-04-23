package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuExtRequestDTO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.service.support.SkuExtSupport;
import com.dmall.pms.service.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SaveSkuAttributeHandler
 * @author: created by hang.yu on 2019/12/16 16:43
 */
@Component
public class SaveSkuExtHandler extends AbstractCommonHandler<SaveSkuExtRequestDTO, SkuExtDO, Long> {

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Override
    public BaseResult validate(SaveSkuExtRequestDTO requestDTO) {
        return skuSupport.validate(requestDTO.getProductId(), requestDTO.getSkuId());
    }

    @Override
    public BaseResult processor(SaveSkuExtRequestDTO requestDTO) {
        SkuExtDO skuExtDO = skuExtSupport.getBySkuId(requestDTO.getSkuId());
        if (skuExtDO == null) {
            skuExtDO = dtoConvertDo(requestDTO, SkuExtDO.class);
            skuExtMapper.insert(skuExtDO);
        } else {
            skuExtDO.setDetailHtml(requestDTO.getDetailHtml());
            skuExtDO.setDetailMobileHtml(requestDTO.getDetailMobileHtml());
            skuExtMapper.updateById(skuExtDO);
        }
        return ResultUtil.success(requestDTO.getSkuId());
    }
}

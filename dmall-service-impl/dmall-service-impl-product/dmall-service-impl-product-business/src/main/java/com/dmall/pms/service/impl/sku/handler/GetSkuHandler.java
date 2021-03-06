package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.SkuAttributeValueSupport;
import com.dmall.pms.service.support.SkuExtSupport;
import com.dmall.pms.service.support.SkuMediaSupport;
import com.dmall.pms.service.support.SkuStockSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class GetSkuHandler extends AbstractCommonHandler<Long, SkuDO, GetSkuResponseDTO> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuMediaSupport skuMediaSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Autowired
    private SkuStockSupport skuStockSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<GetSkuResponseDTO> processor(Long id) {
        SkuDO skuDO = pmsValidate.validateSku(id);
        GetSkuResponseDTO getSkuResponseDTO = new GetSkuResponseDTO();
        // sku基本信息
        getSkuResponseDTO.setBasicSku(getBasicSkuResponseDTO(skuDO));
        // sku媒体列表信息
        getSkuResponseDTO.setMediaList(skuMediaSupport.listBySkuId(id));
        // sku属性值信息
        getSkuResponseDTO
            .setSkuAttributeValue(skuAttributeValueSupport.getSkuAttributeValue(skuDO.getId(), skuDO.getBrandId()));
        // sku扩展信息
        getSkuResponseDTO.setSkuExt(skuExtSupport.getSkuExt(id));
        return ResultUtil.success(getSkuResponseDTO);
    }

    private BasicSkuResponseDTO getBasicSkuResponseDTO(SkuDO skuDO) {
        BasicSkuResponseDTO basicSku = BeanUtil.copyProperties(skuDO, BasicSkuResponseDTO.class);
        basicSku.setNewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getNewStatus()));
        basicSku.setRecommendStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getRecommendStatus()));
        basicSku.setPreviewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getPreviewStatus()));
        basicSku.setPublishStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getPublishStatus()));
        basicSku.setSalableStock(skuStockSupport.getSaleableStock(skuDO.getId()));
        basicSku.setLowStock(basicSku.getStock() - basicSku.getSalableStock());
        return basicSku;
    }

}

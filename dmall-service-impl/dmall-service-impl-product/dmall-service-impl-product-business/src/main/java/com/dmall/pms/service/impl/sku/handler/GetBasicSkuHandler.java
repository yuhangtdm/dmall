package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.SkuExtSupport;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 查询sku处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class GetBasicSkuHandler extends AbstractCommonHandler<List<Long>, SkuDO, List<BasicSkuResponseDTO>> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Override
    public BaseResult<List<BasicSkuResponseDTO>> processor(List<Long> ids) {
        List<BasicSkuResponseDTO> result = Lists.newArrayList();
        for (Long id : ids) {
            SkuDO skuDO = skuMapper.selectById(id);
            if (skuDO == null) {
                return ResultUtil.fail(PmsErrorEnum.SKU_NOT_EXISTS);
            }
            result.add(getBasicSkuResponseDTO(skuDO));
        }

        return ResultUtil.success(result);
    }

    private BasicSkuResponseDTO getBasicSkuResponseDTO(SkuDO skuDO) {
        BasicSkuResponseDTO basicSku = BeanUtil.copyProperties(skuDO, BasicSkuResponseDTO.class);
        ProductDO productDO = productMapper.selectById(skuDO.getProductId());
        basicSku.setAuditStatus(EnumUtil.getCodeDescEnum(SkuAuditStatusEnum.class, skuDO.getAuditStatus()));
        basicSku.setNewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getNewStatus()));
        basicSku.setRecommendStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getRecommendStatus()));
        basicSku.setPreviewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getPreviewStatus()));
        basicSku.setPublishStatus(EnumUtil.getCodeDescEnum(YNEnum.class, skuDO.getPublishStatus()));
        basicSku.setSkuSpecificationsJson(skuExtSupport.getBySkuId(basicSku.getId()).getSkuSpecificationsJson());
        basicSku.setWeight(productDO.getWeight() + productDO.getUnit());
        return basicSku;
    }

}

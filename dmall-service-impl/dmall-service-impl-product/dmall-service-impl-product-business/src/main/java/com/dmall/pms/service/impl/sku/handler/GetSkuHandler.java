package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.enums.base.YNEnum;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.support.CategorySupport;
import com.dmall.pms.service.impl.product.attribute.ProductAttributeSupport;
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
    private CategorySupport categorySupport;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Autowired
    private ProductAttributeSupport productAttributeSupport;

//    @Autowired
//    private SkuAttributeMapper skuAttributeMapper;

    @Autowired
    private SaveSkuAttributeHandler saveSkuAttributeHandler;

    @Override
    public BaseResult<GetSkuResponseDTO> processor(Long id) {
        SkuDO skuDO = skuMapper.selectById(id);
        BasicSkuResponseDTO basicSku = BeanUtil.copyProperties(skuDO, BasicSkuResponseDTO.class);
//        basicSku.setCascadeCategoryName(categorySupport.getCascadeCategoryName(skuDO.getCategoryId()));
        basicSku.setAuditStatus(EnumUtil.getKeyValueEnum(SkuAuditStatusEnum.class, skuDO.getAuditStatus()));
        basicSku.setRecommendStatus(EnumUtil.getKeyValueEnum(YNEnum.class, skuDO.getRecommendStatus()));
        basicSku.setBrandName(brandCacheService.selectById(skuDO.getBrandId()).getName());
        basicSku.setNewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, skuDO.getNewStatus()));
        basicSku.setPreviewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, skuDO.getPreviewStatus()));
        GetSkuResponseDTO getSkuResponseDTO = new GetSkuResponseDTO();
        getSkuResponseDTO.setBasicSku(basicSku);
        SkuExtDO skuExtDO = skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, id));
        getSkuResponseDTO.setDetailHtml(skuExtDO.getDetailHtml());
        getSkuResponseDTO.setDetailMobileHtml(skuExtDO.getDetailMobileHtml());
//        GetProductAttributeResponseDTO responseDTO = productAttributeSupport.setSaleAttribute(skuDO.getProductId());
//        List<SkuAttributeDO> skuAttributeDOList = skuAttributeMapper.selectList(Wrappers.<SkuAttributeDO>lambdaQuery().eq(SkuAttributeDO::getSkuId, id));
//        List<Long> attributeValueList = skuAttributeDOList.stream().map(SkuAttributeDO::getProductAttributeId).collect(Collectors.toList());
//        saveSkuAttributeHandler.changeProductAttribute(responseDTO, attributeValueList);
//        getSkuResponseDTO.setSpecifications(responseDTO.getSpecifications());
//        getSkuResponseDTO.setParams(responseDTO.getParams());
        return ResultUtil.success();
    }

}

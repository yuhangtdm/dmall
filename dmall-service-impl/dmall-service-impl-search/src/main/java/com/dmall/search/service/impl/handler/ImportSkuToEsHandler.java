package com.dmall.search.service.impl.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.*;
import com.dmall.search.api.dto.AttributeDTO;
import com.dmall.search.api.dto.AttributeValueDTO;
import com.dmall.search.api.dto.BrandDTO;
import com.dmall.search.service.impl.EsConstants;
import com.dmall.search.service.impl.es.SkuEsDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 导入es处理器
 * @author: created by hang.yu on 2020/3/5 22:02
 */
@Component
public class ImportSkuToEsHandler {

    @Autowired
    private ESDao esDao;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    public void importAllSku() {
        List<SkuDO> skuDOS = skuMapper.selectList(Wrappers.emptyWrapper());
        for (int i = 0; i < skuDOS.size(); i++) {
            SkuDO skuDO = skuDOS.get(i);
            ProductDO productDO = productMapper.selectById(skuDO.getProductId());
            List<Long> categoryIds = categoryProductMapper.selectList(Wrappers.<CategoryProductDO>lambdaQuery().eq(CategoryProductDO::getProductId, productDO.getId()))
                    .stream().map(CategoryProductDO::getCategoryId).collect(Collectors.toList());

            SkuEsDTO skuEsDTO = new SkuEsDTO();
            skuEsDTO.setSkuId(skuDO.getId());
            skuEsDTO.setSkuName(skuDO.getName());
            skuEsDTO.setSkuNo(skuDO.getSkuNo());
            skuEsDTO.setSkuPrice(skuDO.getPrice());
            skuEsDTO.setSkuSubName(skuDO.getSubName());
            skuEsDTO.setSkuDescription(skuDO.getDescription());
            skuEsDTO.setSkuMainPic(skuDO.getPic());
            skuEsDTO.setSkuStock(skuDO.getStock());
            skuEsDTO.setSkuCommentCount(999L);
            skuEsDTO.setSkuSaleCount(1999L);
            skuEsDTO.setSkuOnPublishTime(DateUtil.format(skuDO.getOnPublishTime(), DatePattern.NORM_DATE_PATTERN));
            skuEsDTO.setProductId(skuDO.getProductId());
            skuEsDTO.setProductNo(productDO.getProductNo());
            skuEsDTO.setProductName(productDO.getName());
            skuEsDTO.setCategoryIds(categoryIds);

            BrandDO brandDO = brandMapper.selectById(skuDO.getBrandId());
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setBrandId(brandDO.getId());
            brandDTO.setBrandName(brandDO.getName());
            brandDTO.setBrandEnglishName(brandDO.getEnglishName());
            brandDTO.setBrandLogo(brandDO.getLogo());
            skuEsDTO.setBrandDTO(brandDTO);

            List<AttributeDTO> attributeList = Lists.newArrayList();

            List<Long> attributeValues = skuAttributeValueMapper.selectList(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                    .eq(SkuAttributeValueDO::getSkuId, skuDO.getId())).stream()
                    .distinct()
                    .map(SkuAttributeValueDO::getProductAttributeValueId)
                    .collect(Collectors.toList());
            List<ProductAttributeValueDO> productAttributeValues = productAttributeValueMapper.selectBatchIds(attributeValues);


            // 按照属性分组
            Map<Long, List<ProductAttributeValueDO>> attributeMap =
                    productAttributeValues.stream().collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
            for (Map.Entry<Long, List<ProductAttributeValueDO>> entry : attributeMap.entrySet()) {
                Long attrId = entry.getKey();
                List<ProductAttributeValueDO> v = entry.getValue();
                for (Long categoryId : categoryIds) {
                    // 去数据库查询是否可选
                    CategoryAttributeDO categoryAttributeDO = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                            .eq(CategoryAttributeDO::getCategoryId, categoryId)
                            .eq(CategoryAttributeDO::getAttributeId, attrId));
                    if (categoryAttributeDO != null){
                        Integer canScreen = categoryAttributeDO.getCanScreen();
                        // 可搜索
                        if (!canScreen.equals(1)) {
                            AttributeDTO attributeDTO = new AttributeDTO();
                            attributeDTO.setAttributeId(attrId);
                            attributeDTO.setAttributeName(attributeMapper.selectById(attrId).getShowName());
                            attributeDTO.setCanScreen(canScreen);
                            List<AttributeValueDTO> attributeValueList = Lists.newArrayList();
                            for (ProductAttributeValueDO productAttributeValueDO : v) {
                                AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
                                attributeValueDTO.setAttributeValueId(productAttributeValueDO.getId());
                                attributeValueDTO.setAttributeValueName(productAttributeValueDO.getAttributeValue());
                                if (!attributeValueList.contains(attributeValueDTO)) {
                                    attributeValueList.add(attributeValueDTO);
                                }
                            }
                            attributeDTO.setAttributeValues(attributeValueList);
                            attributeList.add(attributeDTO);
                        }
                    }
                }
            }
            skuEsDTO.setAttributeValueIds(attributeValues);
            skuEsDTO.setAttributes(attributeList);
            esDao.saveOrUpdate(skuEsDTO, "dmall_sku", EsConstants.TYPE_NAME, skuEsDTO.getSkuId());
        }

    }
}

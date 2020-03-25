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
import com.dmall.search.service.impl.es.EsConstants;
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

    /**
     * 操作es的sku数据
     */
    public void operateEsSku(Long skuId) {
        SkuDO skuDO = skuMapper.selectById(skuId);
        if (skuDO == null){
            // 删除
            esDao.delete(EsConstants.INDEX_NAME, EsConstants.TYPE_NAME, skuId);
        }else {
            // 查询商品
            ProductDO productDO = productMapper.selectById(skuDO.getProductId());
            // 查询分类
            List<Long> categoryIds = categoryProductMapper.selectList(Wrappers.<CategoryProductDO>lambdaQuery().eq(CategoryProductDO::getProductId, productDO.getId()))
                    .stream().map(CategoryProductDO::getCategoryId).collect(Collectors.toList());

            // 构建es对象
            SkuEsDTO skuEsDTO = new SkuEsDTO();
            skuEsDTO.setSkuId(skuDO.getId());
            skuEsDTO.setSkuName(skuDO.getName());
            skuEsDTO.setSkuNo(skuDO.getSkuNo());
            skuEsDTO.setSkuPrice(skuDO.getPrice());
            skuEsDTO.setSkuSubName(skuDO.getSubName());
            skuEsDTO.setSkuDescription(skuDO.getDescription());
            skuEsDTO.setSkuMainPic(skuDO.getPic());
            skuEsDTO.setSkuStock(skuDO.getStock());
            // todo 调用sku评论数量接口
            skuEsDTO.setSkuCommentCount(999L);
            // todo 调用sku销量接口
            skuEsDTO.setSkuSaleCount(1999L);
            skuEsDTO.setSkuOnPublishTime(DateUtil.format(skuDO.getOnPublishTime(), DatePattern.NORM_DATE_PATTERN));
            skuEsDTO.setProductId(skuDO.getProductId());
            skuEsDTO.setProductNo(productDO.getProductNo());
            skuEsDTO.setProductName(productDO.getName());
            skuEsDTO.setCategoryIds(categoryIds);

            // 品牌数据
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
            // 属性值id 用于过滤
            skuEsDTO.setAttributeValueIds(attributeValues);
            // 属性值信息 用于展示
            skuEsDTO.setAttributes(attributeList);
            esDao.saveOrUpdate(skuEsDTO, EsConstants.INDEX_NAME, EsConstants.TYPE_NAME, skuEsDTO.getSkuId());
        }

    }
}

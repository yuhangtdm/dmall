package com.dmall.pms.service.impl.product.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.NoUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.save.*;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.generator.service.IProductAttributeValueService;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 新增商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveProductHandler extends AbstractCommonHandler<SaveProductRequestDTO, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private IProductAttributeValueService iProductAttributeValueService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    @Autowired
    private ProductValidate productValidate;

    @Override
    public BaseResult<Long> validate(SaveProductRequestDTO requestDTO) {
        // 商品名称必须唯一
        ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery()
                .eq(ProductDO::getName, requestDTO.getBasicProduct().getName()));
        if (productDO != null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NAME_EXISTS);
        }

        // 校验商品扩展参数
        return productValidate.validate(requestDTO.getExt());
    }

    @Override
    public BaseResult<Long> processor(SaveProductRequestDTO requestDTO) {
        // 保存商品
        ProductDO productDO = saveProductDO(requestDTO);

        // 保存商品属性值
        saveProductAttributeValue(productDO, requestDTO);

        // 保存sku
        saveSku(productDO.getId(), requestDTO.getExt().getBrandId(), requestDTO.getSkuList());

        return ResultUtil.success(productDO.getId());
    }

    /**
     * 构建商品对象
     */
    private ProductDO saveProductDO(SaveProductRequestDTO requestDTO) {
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        ProductExtDTO productAttribute = requestDTO.getExt();
        ProductDO productDO = BeanUtil.copyProperties(basicProduct, ProductDO.class);
        productDO.setProductNo(NoUtil.generateProductNo());
        productDO.setBrandId(productAttribute.getBrandId());
        productMapper.insert(productDO);
        for (Long categoryId : requestDTO.getExt().getCategoryIds()) {
            CategoryProductDO categoryProductDO = new CategoryProductDO();
            categoryProductDO.setCategoryId(categoryId);
            categoryProductDO.setProductId(productDO.getId());
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            categoryProductDO.setCascadeCategoryId(categoryDO.getPath());
            categoryProductMapper.insert(categoryProductDO);
        }

        return productDO;
    }

    /**
     * 保存商品属性值
     */
    private void saveProductAttributeValue(ProductDO productDO, SaveProductRequestDTO requestDTO) {
        List<ProductAttributeValueDO> list = Lists.newArrayList();
        Long productId = productDO.getId();
        ProductExtDTO productExtDTO = requestDTO.getExt();
        // 规格
        JSONObject productSpecifications = new JSONObject();
        List<SpecificationsDTO> specifications = productExtDTO.getSpecifications();
        for (SpecificationsDTO specification : specifications) {
            JSONArray jsonArray = new JSONArray();
            for (SpecificationsValueDTO specificationsValue : specification.getSpecificationsValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setProductId(productId);
                attributeValueDO.setAttributeId(specification.getAttributeId());
                attributeValueDO.setIsSellingPoint("N");
                attributeValueDO.setIsSpecifications("Y");
                attributeValueDO.setAttributeValue(specificationsValue.getAttributeValue());
                attributeValueDO.setPic(specificationsValue.getPic());
                list.add(attributeValueDO);
                JSONObject object = new JSONObject();
                object.put("value", specificationsValue.getAttributeValue());
                if (StrUtil.isNotBlank(specificationsValue.getPic())) {
                    object.put("pic", specificationsValue.getPic());
                }
                jsonArray.add(object);
            }
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            productSpecifications.put(attributeDO.getShowName(), jsonArray);
        }

        // 卖点
        List<SalePointDTO> salePoints = productExtDTO.getSalePoints();
        for (SalePointDTO salePoint : salePoints) {
            for (String salePointValue : salePoint.getSalePointValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setAttributeId(salePoint.getAttributeId());
                attributeValueDO.setProductId(productId);
                attributeValueDO.setIsSellingPoint("Y");
                attributeValueDO.setIsSpecifications("N");
                attributeValueDO.setAttributeValue(salePointValue);
                list.add(attributeValueDO);
            }
        }

        // 参数
        List<ParamDTO> params = productExtDTO.getParams();
        for (ParamDTO param : params) {
            for (ParamValueDTO paramAttribute : param.getParamAttributes()) {
                for (String paramValue : paramAttribute.getParamValues()) {
                    ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                    attributeValueDO.setProductId(productId);
                    attributeValueDO.setAttributeId(paramAttribute.getAttributeId());
                    attributeValueDO.setIsSellingPoint("N");
                    attributeValueDO.setIsSpecifications("N");
                    attributeValueDO.setAttributeValue(paramValue);
                    list.add(attributeValueDO);
                }
            }
        }

        // 保存商品属性值
        iProductAttributeValueService.saveBatch(list);
        // 修改商品的规格
        productDO.setSpecificationsJson(productSpecifications.toJSONString());
        productMapper.updateById(productDO);
    }

    /**
     * 保存sku
     */
    private void saveSku(Long productId, Long brandId, List<SkuDTO> skuList) {
        for (int i = 0; i < skuList.size(); i++) {
            SkuDTO skuDTO = skuList.get(i);
            SkuDO skuDO = new SkuDO();
            skuDO.setProductId(productId);
            skuDO.setBrandId(brandId);
            skuDO.setSkuNo(NoUtil.generateSkuNo());
            skuDO.setPrice(skuDTO.getPrice());
            skuDO.setStock(skuDTO.getStock());
            skuDO.setSort(i + 1);
            skuMapper.insert(skuDO);
        }

    }

}

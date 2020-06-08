package com.dmall.search.service.impl.es;

/**
 * @description: 常量类
 * @author: created by hang.yu on 2020/3/7 21:12
 */
public interface EsConstants {

    String INDEX_NAME = "dmall_sku";

    String TYPE_NAME = "doc";

    String[] SEARCH_FIELDS = new String[] {"skuName", "skuSubName", "skuDescription", "productName"};

    String FILTER_ATTR_VALUE = "attributeValueIds";

    String FILTER_CATEGORY = "categoryIds";

    String FILTER_BRAND = "brandDTO.brandId";

    String RANGE_PRICE = "price";

    String PERCENT_SIGN = "%";
}

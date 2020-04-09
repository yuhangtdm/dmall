package com.dmall.oms.service.impl.order.es;

/**
 * @description: ES常量类
 * @author: created by hang.yu on 2020/3/7 21:12
 */
public interface EsConstants {

    String INDEX_NAME = "dmall_order";

    String TYPE_NAME = "doc";

    String SEARCH_FIELDS = "skuName";

    String FILTER_ORDER_STATUS = "orderStatus";

    String FILTER_ORDER_ID = "orderId";

    String FILTER_CREATOR = "creator";

    String SORT_FIELD = "createTime";

}

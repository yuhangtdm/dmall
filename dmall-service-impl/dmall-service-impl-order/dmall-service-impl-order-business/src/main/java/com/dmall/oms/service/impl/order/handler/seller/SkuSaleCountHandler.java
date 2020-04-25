package com.dmall.oms.service.impl.order.handler.seller;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.service.support.OrderItemSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: sku销量处理器
 * @author: created by hang.yu on 2020/4/25 16:25
 */
@Component
public class SkuSaleCountHandler extends AbstractCommonHandler<Long, OrderItemDO, Integer> {

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Override
    public BaseResult<Integer> processor(Long skuId) {
        List<OrderItemDO> orderItemList = orderItemSupport.listBySkuId(skuId);
        return ResultUtil.success(orderItemList.stream().mapToInt(OrderItemDO::getSkuNumber).sum());
    }
}

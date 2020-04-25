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
 * @description: 商品销量处理器
 * @author: created by hang.yu on 2020/4/25 16:25
 */
@Component
public class ProductSaleCountHandler extends AbstractCommonHandler<Long, OrderItemDO, Integer> {

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Override
    public BaseResult<Integer> processor(Long productId) {
        List<OrderItemDO> orderItemList = orderItemSupport.listByProductId(productId);
        return ResultUtil.success(orderItemList.stream().mapToInt(OrderItemDO::getSkuNumber).sum());
    }
}

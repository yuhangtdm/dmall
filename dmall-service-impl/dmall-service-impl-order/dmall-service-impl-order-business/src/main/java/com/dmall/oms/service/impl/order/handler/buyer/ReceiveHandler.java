package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.CronUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.impl.order.xxljob.XxlJobSupport;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.oms.service.impl.support.SubOrderJobSupport;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import com.dmall.oms.service.impl.support.SyncEsOrderSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 收货处理器
 * @author: created by hang.yu on 2020/4/11 20:35
 */
@Component
public class ReceiveHandler extends AbstractCommonHandler<Long, OrderDO, Long> {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private XxlJobSupport xxlJobSupport;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private SubOrderJobSupport subOrderJobSupport;

    private static final String JOB_DESC = "子订单:{}已确认收货,skuIds:{},15天后自动好评";


    @Override
    public BaseResult<Long> processor(Long subOrderId) {
        // 子订单存在
        SubOrderDO subOrderDO = subOrderMapper.selectById(subOrderId);
        if (subOrderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 订单存在
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getOrderId());
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        // 必须为待收货状态才可以确认收货
        if (!SubOrderStatusEnum.WAIT_SHIP.getCode().equals(subOrderDO.getStatus())) {
            return ResultUtil.fail(OrderErrorEnum.RECEIVER_STATUS);
        }
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        if (!portalMemberDTO.getId().equals(orderDO.getCreator())) {
            return ResultUtil.fail(OrderErrorEnum.NO_AUTHORITY);
        }

        // 修改状态为已收货
        subOrderDO.setStatus(SubOrderStatusEnum.COMPLETED.getCode());
        subOrderDO.setDeliverType(DeliverTypeEnum.HANDLE.getCode());
        subOrderMapper.updateById(subOrderDO);
        // 判断是否需要修改
        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(orderDO.getId());
        List<SubOrderDO> completedList = subOrderList.stream().filter(subOrder ->
                SubOrderStatusEnum.COMPLETED.getCode().equals(subOrder.getStatus()))
                .collect(Collectors.toList());

        if (subOrderList.size() == completedList.size()) {
            orderDO.setStatus(OrderStatusEnum.COMPLETED.getCode());
            orderMapper.updateById(orderDO);
        }
        // 查出该子订单的sku列表
        List<OrderItemDO> orderItemList = orderItemSupport.listBySubOrderId(subOrderId);

        String skuIds = orderItemList.stream().map(OrderItemDO::getSkuId).map(String::valueOf)
                .collect(Collectors.joining(StrUtil.COMMA));

        String param = StrUtil.format("{}:{}", subOrderId, skuIds);
        // 确认收货 15天 自动好评 新增定时任务
        int xxlJobId = xxlJobSupport.addJob(OrderConstants.AUTO_GOOD_COMMENT_HANDLER, param,
                StrUtil.format(JOB_DESC, subOrderId, skuIds), CronUtil.getCronAddDay(OrderConstants.DELAY_DAY));
        subOrderJobSupport.insert(subOrderId, xxlJobId, JobTypeEnum.AUTO_GOOD_COMMENT);
        // 同步到es
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        return ResultUtil.success(subOrderId);
    }


}

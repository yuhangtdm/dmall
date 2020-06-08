package com.dmall.oms.service.impl.order.handler.seller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.demolitionorder.DemolitionDetailRequestDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.support.OrderLogSupport;
import com.dmall.oms.service.support.SyncEsOrderSupport;
import com.dmall.oms.service.validate.OmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 拆单处理器
 * @author: created by hang.yu on 2020/4/5 10:19
 */
@Component
public class DemolitionOrderHandler extends AbstractCommonHandler<DemolitionOrderRequestDTO, SubOrderDO, Long> {

    private static final String LOG_CONTENT = "拆单成功,类型:无需拆单";

    private static final String LOG_CONTENT_SPLIT = "拆单成功,子订单号:{}";

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Autowired
    private OmsValidate omsValidate;

    /**
     * 拆单逻辑：
     * 主订单只能拆一次
     * 需要拆单时 根据订单明细来拆
     * 不需要拆单 则指定发货仓库
     */
    @Override
    public BaseResult<Long> processor(DemolitionOrderRequestDTO requestDTO) {
        // 拆单方式不能为 未拆分
        if (SplitEnum.NOT.getCode().equals(requestDTO.getSplit())) {
            return ResultUtil.fail(OmsErrorEnum.SPLIT_WAY_ERROR);
        } else {
            // 如果是已拆分 或 无需拆分 拆单明细不能为空
            if (CollUtil.isEmpty(requestDTO.getDetails())) {
                return ResultUtil.fail(OmsErrorEnum.SPLIT_DETAIL_NOT_EMPTY);
            }
        }
        OrderDO orderDO = omsValidate.validateOrder(requestDTO.getOrderId());

        // 已经拆过单不可再拆分
        if (!SplitEnum.NOT.getCode().equals(orderDO.getSplit())) {
            return ResultUtil.fail(OmsErrorEnum.SPLIT);
        }
        orderDO.setSplit(requestDTO.getSplit());
        orderDO.setSplitReason(requestDTO.getSplitReason());
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        orderDO.setSplitPerson(portalMemberDTO.getId());
        orderMapper.updateById(orderDO);

        StringBuilder subOrderIds = new StringBuilder();
        // 已拆分和无需拆分都生成子订单记录
        for (DemolitionDetailRequestDTO detail : requestDTO.getDetails()) {
            SubOrderDO subOrderDO = new SubOrderDO();
            subOrderDO.setId(orderDO.getId());
            subOrderDO.setOrderId(orderDO.getId());
            // 子订单状态默认 待发货 评价状态:未评价
            subOrderDO.setStatus(SubOrderStatusEnum.WAIT_SHIP.getCode());
            subOrderDO.setCommentStatus(OrderCommentStatusEnum.NO.getCode());
            subOrderDO.setWarehouseId(detail.getWarehouseId());
            subOrderDO.setDeliverId(detail.getDeliveryId());
            subOrderMapper.insert(subOrderDO);
            for (Long orderItemId : detail.getOrderItemIds()) {
                OrderItemDO orderItemDO = orderItemMapper.selectById(orderItemId);
                if (orderItemDO == null) {
                    throw new BusinessException(OmsErrorEnum.ORDER_NOT_EXISTS);
                }
                orderItemDO.setSubOrderId(subOrderDO.getId());
                orderItemMapper.updateById(orderItemDO);
            }
            subOrderIds.append(subOrderDO.getId()).append(",");
        }
        if (SplitEnum.NOT_NEED.getCode().equals(requestDTO.getSplit())) {
            orderLogSupport.insert(orderDO.getId(), OrderOperateEnum.SPLIT, true, LOG_CONTENT);
        } else {
            orderLogSupport.insert(orderDO.getId(), OrderOperateEnum.SPLIT, true,
                StrUtil.format(LOG_CONTENT_SPLIT, subOrderIds.deleteCharAt(subOrderIds.lastIndexOf(","))));
        }
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        return ResultUtil.success(requestDTO.getOrderId());
    }
}

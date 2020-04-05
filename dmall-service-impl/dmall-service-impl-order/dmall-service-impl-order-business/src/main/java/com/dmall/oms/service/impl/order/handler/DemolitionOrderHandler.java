package com.dmall.oms.service.impl.order.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.demolitionorder.DemolitionDetailRequestDTO;
import com.dmall.oms.api.dto.demolitionorder.DemolitionOrderRequestDTO;
import com.dmall.oms.api.enums.DeliverStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 拆单处理器
 * @author: created by hang.yu on 2020/4/5 10:19
 */
@Component
public class DemolitionOrderHandler extends AbstractCommonHandler<DemolitionOrderRequestDTO, SubOrderDO, Long> {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 拆单逻辑：
     * 主订单只能拆一次
     *  需要拆单时 根据订单明细来拆
     *  不需要拆单 则指定发货仓库
     */
    @Override
    public BaseResult<Long> processor(DemolitionOrderRequestDTO requestDTO) {
        OrderDO orderDO = orderMapper.selectById(requestDTO.getOrderId());
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }

        // 已经拆过单
        if (!SplitEnum.NOT.getCode().equals(orderDO.getSplit())) {
            return ResultUtil.fail(OrderErrorEnum.SPLITED);
        }
        if (SplitEnum.NOT.getCode().equals(requestDTO.getIsSplit())) {
            return ResultUtil.fail(OrderErrorEnum.SPLIT_WAY_ERROR);
        } else {
            if (CollUtil.isEmpty(requestDTO.getDetails())) {
                return ResultUtil.fail(OrderErrorEnum.SPLIT_DETAIL_NOT_EMPTY);
            }
        }
        orderDO.setSplit(requestDTO.getIsSplit());
        orderDO.setSplitReason(requestDTO.getSplitReason());
        PortalMemberDTO portalMemberDTO = PortalMemberContextHolder.get();
        orderDO.setSplitPerson(portalMemberDTO.getId());
        orderMapper.updateById(orderDO);

        // 已拆分和无需拆分都生成子订单记录
        for (DemolitionDetailRequestDTO detail : requestDTO.getDetails()) {
            SubOrderDO subOrderDO = new SubOrderDO();
            if (SplitEnum.IS.getCode().equals(requestDTO.getIsSplit())) {
                subOrderDO.setId(IdGeneratorUtil.snowflakeId());
            } else {
                subOrderDO.setId(orderDO.getId());
            }
            subOrderDO.setOrderId(orderDO.getId());
            subOrderDO.setOrderItemId(detail.getOrderItemId());
            subOrderDO.setDeliverStatus(DeliverStatusEnum.N.getCode());
            subOrderDO.setWarehouseId(detail.getWarehouseId());
            subOrderDO.setDeliverId(detail.getDeliveryId());
            subOrderMapper.insert(subOrderDO);
        }


        return ResultUtil.success(requestDTO.getOrderId());
    }
}

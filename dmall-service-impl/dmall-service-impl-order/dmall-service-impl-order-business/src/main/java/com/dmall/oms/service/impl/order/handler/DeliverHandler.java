package com.dmall.oms.service.impl.order.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.bms.api.dto.deliverwarehouse.CommonDeliverWarehouseResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.deliver.DeliverRequestDTO;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.feign.DeliverWarehouseFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.support.OrderLogSupport;
import com.dmall.oms.service.impl.support.OrderStatusSupport;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: 发货处理器
 * @author: created by hang.yu on 2020/4/5 15:53
 */
@Component
public class DeliverHandler extends AbstractCommonHandler<DeliverRequestDTO, SubOrderDO, Long> {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private DeliverWarehouseFeign deliverWarehouseFeign;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    private static final String LOG_CONTENT = "子订单:{}已发货";

    private static final String ORDER_LOG_CONTENT = "订单已全部发货";

    @Override
    public BaseResult<Long> processor(DeliverRequestDTO requestDTO) {
        SubOrderDO subOrderDO = subOrderMapper.selectById(requestDTO.getSubOrderId());
        if (subOrderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.SUB_ORDER_NOT_EXISTS);
        }
        subOrderDO.setDeliverStatus(DeliverStatusEnum.Y.getCode());
        subOrderDO.setLogisticsNo(requestDTO.getLogisticsNo());
        subOrderDO.setLogisticsCompany(requestDTO.getLogisticsCompany());
        subOrderDO.setExpressFee(requestDTO.getExpressFee());
        AdminUserDTO adminUser = AdminUserContextHolder.get();
        if (adminUser.getWarehouseId() == null) {
            return ResultUtil.fail(OrderErrorEnum.DELIVER_PERSON_WAREHOUSE_EMPTY);
        }
        BaseResult<CommonDeliverWarehouseResponseDTO> warehouseBaseResult = deliverWarehouseFeign.get(adminUser.getWarehouseId());
        if (!warehouseBaseResult.getResult()) {
            return ResultUtil.fail(warehouseBaseResult.getCode(), warehouseBaseResult.getMsg());
        }
        CommonDeliverWarehouseResponseDTO data = warehouseBaseResult.getData();
        subOrderDO.setDeliverProvince(data.getProvince());
        subOrderDO.setDeliverCity(data.getCity());
        subOrderDO.setDeliverRegion(data.getRegion());
        subOrderDO.setDeliverDetailAddress(data.getDetailAddress());
        subOrderDO.setDeliverTime(new Date());
        subOrderMapper.updateById(subOrderDO);

        List<SubOrderDO> subOrderList = subOrderSupport.listByOrderId(subOrderDO.getOrderId());
        Optional<SubOrderDO> any = subOrderList.stream()
                .filter(subOrder -> DeliverStatusEnum.N.getCode().equals(subOrder.getDeliverStatus())).findAny();
        orderLogSupport.insert(subOrderDO.getOrderId(), OrderOperateEnum.DELIVER, true, subOrderDO.getId(),
                StrUtil.format(LOG_CONTENT, subOrderDO.getId()));
        OrderDO orderDO = orderMapper.selectById(subOrderDO.getId());
        orderDO.setDeliverStatus(OrderDeliverStatusEnum.PART.getCode());
        if (!any.isPresent()) {
            orderDO.setStatus(OrderStatusEnum.WAIT_RECEIVE.getCode());
            orderDO.setDeliverStatus(OrderDeliverStatusEnum.ALL.getCode());
            orderStatusSupport.insert(orderDO.getId(), OrderStatusEnum.WAIT_RECEIVE.getCode());
            orderLogSupport.insert(subOrderDO.getOrderId(), OrderOperateEnum.DELIVER, true, ORDER_LOG_CONTENT);
        }
        orderMapper.updateById(orderDO);
        return ResultUtil.success(subOrderDO.getId());
    }
}

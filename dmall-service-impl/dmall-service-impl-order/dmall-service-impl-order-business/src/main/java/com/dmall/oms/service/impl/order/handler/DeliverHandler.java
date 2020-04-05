package com.dmall.oms.service.impl.order.handler;

import com.dmall.bms.api.dto.deliverwarehouse.CommonDeliverWarehouseResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.deliver.DeliverRequestDTO;
import com.dmall.oms.api.enums.DeliverStatusEnum;
import com.dmall.oms.api.enums.OrderErrorEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.feign.DeliverWarehouseFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
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

    @Override
    public BaseResult<Long> processor(DeliverRequestDTO requestDTO) {
        SubOrderDO subOrderDO = subOrderMapper.selectById(requestDTO.getSubOrderId());
        if (subOrderDO == null){
            return ResultUtil.fail(OrderErrorEnum.SUB_ORDER_NOT_EXISTS);
        }
        subOrderDO.setDeliverStatus(DeliverStatusEnum.Y.getCode());
        subOrderDO.setLogisticsNo(requestDTO.getLogisticsNo());
        AdminUserDTO adminUser = AdminUserContextHolder.get();
        if (adminUser.getWarehouseId() == null){
            return ResultUtil.fail(OrderErrorEnum.DELIVER_PERSON_WAREHOUSE_EMPTY);
        }
        BaseResult<CommonDeliverWarehouseResponseDTO> warehouseBaseResult = deliverWarehouseFeign.get(adminUser.getWarehouseId());
        if (!warehouseBaseResult.getResult()){
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
        if (!any.isPresent()){
            OrderDO orderDO = orderMapper.selectById(subOrderDO.getId());
            orderDO.setStatus(OrderStatusEnum.WAIT_RECEIVE.getCode());
            orderMapper.updateById(orderDO);
        }
        return ResultUtil.success(subOrderDO.getId());
    }
}

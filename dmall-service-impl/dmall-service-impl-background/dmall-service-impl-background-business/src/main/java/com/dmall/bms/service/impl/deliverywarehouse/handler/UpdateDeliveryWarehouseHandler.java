package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.request.UpdateDeliveryWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商家发货仓库处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class UpdateDeliveryWarehouseHandler extends AbstractCommonHandler<UpdateDeliveryWarehouseRequestDTO, DeliveryWarehouseDO, Long> {

    @Autowired
    private DeliveryWarehouseMapper deliveryWarehouseMapper;

    @Override
    public BaseResult<Long> validate(UpdateDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.request.SaveDeliveryWarehouseRequestDTO;
import com.dmall.bms.service.impl.deliverywarehouse.enums.DeliveryWarehouseErrorEnum;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商家发货仓库处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class SaveDeliveryWarehouseHandler extends AbstractCommonHandler<SaveDeliveryWarehouseRequestDTO, DeliveryWarehouseDO, Long> {

    @Autowired
    private DeliveryWarehouseMapper deliveryWarehouseMapper;

    @Override
    public BaseResult<Long> validate(SaveDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseResponseDTO;
import com.dmall.bms.service.impl.deliverywarehouse.enums.DeliveryWarehouseErrorEnum;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商家发货仓库处理器
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Component
public class GetDeliveryWarehouseHandler extends AbstractCommonHandler<Long, DeliveryWarehouseDO, CommonDeliveryWarehouseResponseDTO> {

    @Autowired
    private DeliveryWarehouseMapper deliveryWarehouseMapper;

    @Override
    public BaseResult<CommonDeliveryWarehouseResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonDeliveryWarehouseResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}

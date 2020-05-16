package com.dmall.bms.service.impl.deliverwarehouse.handler;

import com.dmall.bms.api.dto.deliverwarehouse.UpdateDeliverWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.bms.service.support.DeliverWarehouseSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商家发货仓库处理器
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Component
public class UpdateDeliverWarehouseHandler extends AbstractCommonHandler<UpdateDeliverWarehouseRequestDTO, DeliverWarehouseDO, Long> {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    @Autowired
    private DeliverWarehouseSupport deliverWarehouseSupport;

    @Override
    public BaseResult<Long> processor(UpdateDeliverWarehouseRequestDTO requestDTO) {
        DeliverWarehouseDO deliverWarehouseDO = deliverWarehouseSupport.validateWarehouse(requestDTO.getId());
        DeliverWarehouseDO deliverWarehouse = dtoConvertDo(requestDTO, DeliverWarehouseDO.class);
        deliverWarehouseSupport.updateAddress(deliverWarehouseDO.getMerchantsId(), requestDTO.getDeliverAddress(),
                requestDTO.getReceiveAddress());
        deliverWarehouseMapper.updateById(deliverWarehouse);
        return ResultUtil.success(requestDTO.getId());
    }

}
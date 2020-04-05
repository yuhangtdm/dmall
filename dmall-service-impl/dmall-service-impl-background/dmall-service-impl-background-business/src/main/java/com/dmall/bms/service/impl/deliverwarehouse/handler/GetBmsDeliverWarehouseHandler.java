package com.dmall.bms.service.impl.deliverwarehouse.handler;

import com.dmall.bms.api.dto.deliverwarehouse.CommonDeliverWarehouseResponseDTO;
import com.dmall.bms.api.enums.DeliverWarehouseErrorEnum;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商家发货仓库处理器
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Component
public class GetBmsDeliverWarehouseHandler extends AbstractCommonHandler<Long, DeliverWarehouseDO, CommonDeliverWarehouseResponseDTO> {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    @Override
    public BaseResult<CommonDeliverWarehouseResponseDTO> processor(Long id) {
        DeliverWarehouseDO deliverWarehouseDO = deliverWarehouseMapper.selectById(id);
        if (deliverWarehouseDO == null) {
            return ResultUtil.fail(DeliverWarehouseErrorEnum.DELIVER_WAREHOUSE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(deliverWarehouseDO, CommonDeliverWarehouseResponseDTO.class));
    }

}

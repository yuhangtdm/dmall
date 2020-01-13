package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.ListDeliveryWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 商家发货仓库列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Component
public class ListDeliveryWarehouseHandler extends AbstractCommonHandler<ListDeliveryWarehouseRequestDTO, DeliveryWarehouseDO, CommonDeliveryWarehouseResponseDTO> {

    @Autowired
    private DeliveryWarehouseMapper deliveryWarehouseMapper;

    @Override
    public BaseResult<List<CommonDeliveryWarehouseResponseDTO>> validate(ListDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonDeliveryWarehouseResponseDTO>> processor(ListDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

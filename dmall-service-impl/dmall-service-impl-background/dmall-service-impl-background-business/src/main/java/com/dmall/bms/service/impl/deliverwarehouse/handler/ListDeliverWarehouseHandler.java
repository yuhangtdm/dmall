package com.dmall.bms.service.impl.deliverwarehouse.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.deliverwarehouse.DeliverWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverwarehouse.ListDeliverWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商家发货仓库列表处理器
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Component
public class ListDeliverWarehouseHandler
    extends AbstractCommonHandler<ListDeliverWarehouseRequestDTO, DeliverWarehouseDO, DeliverWarehouseResponseDTO> {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    @Override
    public BaseResult<List<DeliverWarehouseResponseDTO>> processor(ListDeliverWarehouseRequestDTO requestDTO) {
        List<DeliverWarehouseDO> list = deliverWarehouseMapper.selectList(Wrappers.<DeliverWarehouseDO>lambdaQuery()
            .eq(DeliverWarehouseDO::getMerchantsId, requestDTO.getMerchantsId()));
        List<DeliverWarehouseResponseDTO> result = list.stream()
            .map(deliverWarehouseDO -> doConvertDto(deliverWarehouseDO, DeliverWarehouseResponseDTO.class))
            .collect(Collectors.toList());
        return ResultUtil.success(result);
    }

}

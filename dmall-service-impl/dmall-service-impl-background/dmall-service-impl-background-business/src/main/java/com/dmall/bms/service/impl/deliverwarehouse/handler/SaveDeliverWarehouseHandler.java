package com.dmall.bms.service.impl.deliverwarehouse.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.deliverwarehouse.SaveDeliverWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.common.enums.YNEnum;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商家发货仓库处理器
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Component
public class SaveDeliverWarehouseHandler extends AbstractCommonHandler<SaveDeliverWarehouseRequestDTO, DeliverWarehouseDO, Long> {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    @Override
    public BaseResult<Long> processor(SaveDeliverWarehouseRequestDTO requestDTO) {
        DeliverWarehouseDO deliverWarehouseDO = dtoConvertDo(requestDTO, DeliverWarehouseDO.class);
        if (StrUtil.isBlank(requestDTO.getDeliverAddress())) {
            deliverWarehouseDO.setDeliveryStatus(YNEnum.N.getCode());
        }
        if (StrUtil.isBlank(requestDTO.getReceiveAddress())) {
            deliverWarehouseDO.setReceiveStatus(YNEnum.N.getCode());
        }
        if (YNEnum.Y.getCode().equals(requestDTO.getDeliverAddress())) {
            DeliverWarehouseDO updateDO = new DeliverWarehouseDO();
            updateDO.setDeliveryStatus(YNEnum.N.getCode());
            deliverWarehouseMapper.update(updateDO, Wrappers.lambdaUpdate(new DeliverWarehouseDO()
                    .setMerchantsId(requestDTO.getMerchantsId())
                    .setDeliveryStatus(YNEnum.Y.getCode())));
        }
        if (YNEnum.N.getCode().equals(requestDTO.getReceiveAddress())) {
            DeliverWarehouseDO updateDO = new DeliverWarehouseDO();
            updateDO.setReceiveStatus(YNEnum.N.getCode());
            deliverWarehouseMapper.update(updateDO, Wrappers.lambdaUpdate(new DeliverWarehouseDO()
                    .setMerchantsId(requestDTO.getMerchantsId())
                    .setReceiveStatus(YNEnum.Y.getCode())));
        }
        deliverWarehouseMapper.insert(deliverWarehouseDO);
        return ResultUtil.success(deliverWarehouseDO.getId());
    }

}

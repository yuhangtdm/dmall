package com.dmall.bms.service.impl.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.enums.DeliverWarehouseErrorEnum;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: DeliverWarehouseSupport
 * @author: created by hang.yu on 2020/4/5 16:32
 */
@Component
public class DeliverWarehouseSupport {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    public void validateWarehouse(Long id){
        DeliverWarehouseDO deliverWarehouseDO = deliverWarehouseMapper.selectById(id);
        if (deliverWarehouseDO == null) {
            throw new BusinessException(DeliverWarehouseErrorEnum.DELIVER_WAREHOUSE_NOT_EXIST);
        }
    }

    public void updateAddress(DeliverWarehouseDO deliverWarehouseDO, String deliverAddress,
                              String receiveAddress) {
        if (StrUtil.isBlank(deliverAddress)) {
            deliverWarehouseDO.setDeliveryStatus(YNEnum.N.getCode());
        }
        if (StrUtil.isBlank(receiveAddress)) {
            deliverWarehouseDO.setReceiveStatus(YNEnum.N.getCode());
        }
        if (YNEnum.Y.getCode().equals(deliverAddress)) {
            DeliverWarehouseDO updateDO = new DeliverWarehouseDO();
            updateDO.setDeliveryStatus(YNEnum.N.getCode());
            deliverWarehouseMapper.update(updateDO, Wrappers.lambdaUpdate(new DeliverWarehouseDO()
                    .setMerchantsId(deliverWarehouseDO.getMerchantsId())
                    .setDeliveryStatus(YNEnum.Y.getCode())));
        }
        if (YNEnum.N.getCode().equals(receiveAddress)) {
            DeliverWarehouseDO updateDO = new DeliverWarehouseDO();
            updateDO.setReceiveStatus(YNEnum.N.getCode());
            deliverWarehouseMapper.update(updateDO, Wrappers.lambdaUpdate(new DeliverWarehouseDO()
                    .setMerchantsId(deliverWarehouseDO.getMerchantsId())
                    .setReceiveStatus(YNEnum.Y.getCode())));
        }
    }

}

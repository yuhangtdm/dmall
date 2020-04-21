package com.dmall.bms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
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

    /**
     * 校验仓库地址必须存在
     */
    public DeliverWarehouseDO validateWarehouse(Long id) {
        DeliverWarehouseDO deliverWarehouseDO = deliverWarehouseMapper.selectById(id);
        if (deliverWarehouseDO == null) {
            throw new BusinessException(BackGroundErrorEnum.DELIVER_WAREHOUSE_NOT_EXIST);
        }
        return deliverWarehouseDO;
    }

    /**
     * 修改该店铺之前添加的默认地址
     */
    public void updateAddress(Long merchantsId, String deliverAddress, String receiveAddress) {
        // 如果 默认发货地址,将该店铺之前添加的默认发货地址改为否
        if (YNEnum.Y.getCode().equals(deliverAddress)) {
            deliverWarehouseMapper.update(null, Wrappers.<DeliverWarehouseDO>lambdaUpdate()
                    .set(DeliverWarehouseDO::getDeliveryStatus, YNEnum.N.getCode())
                    .eq(DeliverWarehouseDO::getMerchantsId, merchantsId)
                    .eq(DeliverWarehouseDO::getDeliveryStatus, YNEnum.Y.getCode()));
        }
        // 如果 默认收货地址,将该店铺之前添加的默认收货地址改为否
        if (YNEnum.Y.getCode().equals(receiveAddress)) {
            deliverWarehouseMapper.update(null, Wrappers.<DeliverWarehouseDO>lambdaUpdate()
                    .set(DeliverWarehouseDO::getReceiveStatus, YNEnum.N.getCode())
                    .eq(DeliverWarehouseDO::getMerchantsId, merchantsId)
                    .eq(DeliverWarehouseDO::getReceiveStatus, YNEnum.Y.getCode()));
        }
    }

}

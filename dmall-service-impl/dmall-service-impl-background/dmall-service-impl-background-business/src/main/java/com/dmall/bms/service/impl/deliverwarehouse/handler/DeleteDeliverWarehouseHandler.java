package com.dmall.bms.service.impl.deliverwarehouse.handler;

import com.dmall.bms.api.enums.DeliverWarehouseErrorEnum;
import com.dmall.bms.generator.dataobject.DeliverWarehouseDO;
import com.dmall.bms.generator.mapper.DeliverWarehouseMapper;
import com.dmall.bms.service.impl.support.DeliverWarehouseSupport;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除商家发货仓库处理器
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Component
public class DeleteDeliverWarehouseHandler extends AbstractCommonHandler<Long, DeliverWarehouseDO, Long> {

    @Autowired
    private DeliverWarehouseMapper deliverWarehouseMapper;

    @Autowired
    private DeliverWarehouseSupport deliverWarehouseSupport;

    @Override
    public BaseResult<Long> processor(Long id) {
        deliverWarehouseSupport.validateWarehouse(id);
        deliverWarehouseMapper.deleteById(id);
        return ResultUtil.success(id);
    }

}

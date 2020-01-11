package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.PageDeliveryWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.dmall.common.dto.LayUiPage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商家发货仓库分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class PageDeliveryWarehouseHandler extends AbstractCommonHandler<PageDeliveryWarehouseRequestDTO, DeliveryWarehouseDO, CommonDeliveryWarehouseResponseDTO> {

    @Autowired
    private DeliveryWarehouseMapper deliveryWarehouseMapper;

    @Override
    public BaseResult<LayUiPage<CommonDeliveryWarehouseResponseDTO>> validate(PageDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonDeliveryWarehouseResponseDTO>> processor(PageDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

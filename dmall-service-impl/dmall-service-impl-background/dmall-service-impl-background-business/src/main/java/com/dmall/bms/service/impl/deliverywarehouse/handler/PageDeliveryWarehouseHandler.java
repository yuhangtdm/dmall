package com.dmall.bms.service.impl.deliverywarehouse.handler;

import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.PageDeliveryWarehouseRequestDTO;
import com.dmall.bms.generator.dataobject.DeliveryWarehouseDO;
import com.dmall.bms.generator.mapper.DeliveryWarehouseMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
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
    public BaseResult<LayuiPage<CommonDeliveryWarehouseResponseDTO>> validate(PageDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonDeliveryWarehouseResponseDTO>> processor(PageDeliveryWarehouseRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

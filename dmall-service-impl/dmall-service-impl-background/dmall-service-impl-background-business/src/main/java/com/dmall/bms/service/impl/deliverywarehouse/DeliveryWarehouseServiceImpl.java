package com.dmall.bms.service.impl.deliverywarehouse;

import com.dmall.bms.api.dto.deliverywarehouse.request.SaveDeliveryWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.UpdateDeliveryWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.ListDeliveryWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverywarehouse.request.PageDeliveryWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverywarehouse.common.CommonDeliveryWarehouseResponseDTO;
import com.dmall.bms.api.service.DeliveryWarehouseService;
import com.dmall.bms.service.impl.deliverywarehouse.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商家发货仓库服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@RestController
public class DeliveryWarehouseServiceImpl implements DeliveryWarehouseService {

    @Autowired
    private SaveDeliveryWarehouseHandler saveDeliveryWarehouseHandler;

    @Autowired
    private DeleteDeliveryWarehouseHandler deleteDeliveryWarehouseHandler;

    @Autowired
    private UpdateDeliveryWarehouseHandler updateDeliveryWarehouseHandler;

    @Autowired
    private GetDeliveryWarehouseHandler getDeliveryWarehouseHandler;

    @Autowired
    private ListDeliveryWarehouseHandler listDeliveryWarehouseHandler;

    @Autowired
    private PageDeliveryWarehouseHandler pageDeliveryWarehouseHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveDeliveryWarehouseRequestDTO requestDTO) {
        return saveDeliveryWarehouseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteDeliveryWarehouseHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateDeliveryWarehouseRequestDTO requestDTO) {
        return updateDeliveryWarehouseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonDeliveryWarehouseResponseDTO> get(Long id) {
        return getDeliveryWarehouseHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonDeliveryWarehouseResponseDTO>> list(@RequestBody ListDeliveryWarehouseRequestDTO requestDTO) {
        return listDeliveryWarehouseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonDeliveryWarehouseResponseDTO>> page(@RequestBody PageDeliveryWarehouseRequestDTO requestDTO) {
        return pageDeliveryWarehouseHandler.handler(requestDTO);
    }

}

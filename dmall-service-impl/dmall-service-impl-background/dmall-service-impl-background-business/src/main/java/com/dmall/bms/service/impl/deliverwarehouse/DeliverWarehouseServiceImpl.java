package com.dmall.bms.service.impl.deliverwarehouse;

import com.dmall.bms.api.dto.deliverwarehouse.CommonDeliverWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverwarehouse.ListDeliverWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverwarehouse.SaveDeliverWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverwarehouse.UpdateDeliverWarehouseRequestDTO;
import com.dmall.bms.api.service.DeliverWarehouseService;
import com.dmall.bms.service.impl.deliverwarehouse.handler.*;
import com.dmall.common.dto.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商家发货仓库服务实现
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@RestController
public class DeliverWarehouseServiceImpl implements DeliverWarehouseService {

    @Autowired
    private SaveDeliverWarehouseHandler saveDeliverWarehouseHandler;

    @Autowired
    private DeleteDeliverWarehouseHandler deleteDeliverWarehouseHandler;

    @Autowired
    private UpdateDeliverWarehouseHandler updateDeliverWarehouseHandler;

    @Autowired
    private GetBmsDeliverWarehouseHandler getBmsDeliverWarehouseHandler;

    @Autowired
    private ListDeliverWarehouseHandler listDeliverWarehouseHandler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> save(@RequestBody SaveDeliverWarehouseRequestDTO requestDTO) {
        return saveDeliverWarehouseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteDeliverWarehouseHandler.handler(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> update(@RequestBody UpdateDeliverWarehouseRequestDTO requestDTO) {
        return updateDeliverWarehouseHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonDeliverWarehouseResponseDTO> get(Long id) {
        return getBmsDeliverWarehouseHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonDeliverWarehouseResponseDTO>> list(@RequestBody ListDeliverWarehouseRequestDTO requestDTO) {
        return listDeliverWarehouseHandler.handler(requestDTO);
    }

}

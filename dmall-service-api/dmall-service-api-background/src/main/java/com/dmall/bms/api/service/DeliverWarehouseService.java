package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.deliverwarehouse.DeliverWarehouseResponseDTO;
import com.dmall.bms.api.dto.deliverwarehouse.ListDeliverWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverwarehouse.SaveDeliverWarehouseRequestDTO;
import com.dmall.bms.api.dto.deliverwarehouse.UpdateDeliverWarehouseRequestDTO;
import com.dmall.common.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商家发货仓库服务
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Api(tags = "商家发货仓库服务")
@RequestMapping("/deliverWarehouse")
public interface DeliverWarehouseService {

    @PostMapping
    @ApiOperation(value = "新增商家发货仓库")
    BaseResult<Long> save(@Valid @RequestBody SaveDeliverWarehouseRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商家发货仓库")
    @ApiImplicitParam(name = "id", value = "商家发货仓库id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改商家发货仓库")
    BaseResult<Long> update(@Valid @RequestBody UpdateDeliverWarehouseRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商家发货仓库")
    @ApiImplicitParam(name = "id", value = "商家发货仓库id", required = true, dataType = "int", paramType = "path")
    BaseResult<DeliverWarehouseResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "商家发货仓库列表")
    BaseResult<List<DeliverWarehouseResponseDTO>> list(@RequestBody ListDeliverWarehouseRequestDTO requestDTO);

}

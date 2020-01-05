package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.merchants.request.ListMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.request.PageMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.common.CommonMerchantsResponseDTO;
import com.dmall.bms.api.dto.merchants.request.SaveMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.request.UpdateMerchantsRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商家店铺表 1期只有一家店铺服务
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Api(tags = "商家店铺表 1期只有一家店铺服务")
@RequestMapping("/merchants")
public interface MerchantsService {

    @PostMapping("/")
    @ApiOperation(value = "新增商家店铺表 1期只有一家店铺")
    BaseResult<Long> save(@Valid @RequestBody SaveMerchantsRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商家店铺表 1期只有一家店铺")
    @ApiImplicitParam(name = "id", value = "商家店铺表 1期只有一家店铺id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改商家店铺表 1期只有一家店铺")
    BaseResult<Long> update(@Valid @RequestBody UpdateMerchantsRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商家店铺表 1期只有一家店铺")
    @ApiImplicitParam(name = "id", value = "商家店铺表 1期只有一家店铺id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMerchantsResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "商家店铺表 1期只有一家店铺列表")
    BaseResult<List<CommonMerchantsResponseDTO>> list(@RequestBody ListMerchantsRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "商家店铺表 1期只有一家店铺分页")
    BaseResult<LayuiPage<CommonMerchantsResponseDTO>> page(@RequestBody PageMerchantsRequestDTO requestDTO);

}

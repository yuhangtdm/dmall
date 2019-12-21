package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.skuattribute.request.ListSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.request.PageSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeResponseDTO;
import com.dmall.pms.api.dto.skuattribute.request.SaveSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.request.UpdateSkuAttributeRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku属性值服务
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Api(tags = "sku属性值服务")
@RequestMapping("/skuAttribute")
public interface SkuAttributeService {

    @PostMapping("/")
    @ApiOperation(value = "新增sku属性值")
    BaseResult<Long> save(@Valid @RequestBody SaveSkuAttributeRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku属性值")
    @ApiImplicitParam(name = "id", value = "sku属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku属性值")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuAttributeRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku属性值")
    @ApiImplicitParam(name = "id", value = "sku属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonSkuAttributeResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "sku属性值列表")
    BaseResult<List<CommonSkuAttributeResponseDTO>> list(@RequestBody ListSkuAttributeRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku属性值分页")
    BaseResult<LayuiPage<CommonSkuAttributeResponseDTO>> page(@RequestBody PageSkuAttributeRequestDTO requestDTO);

}

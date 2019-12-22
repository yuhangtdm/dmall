package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.skuattributevalue.request.ListSkuAttributeValueRequestDTO;
import com.dmall.pms.api.dto.skuattributevalue.request.PageSkuAttributeValueRequestDTO;
import com.dmall.pms.api.dto.skuattributevalue.common.CommonSkuAttributeValueResponseDTO;
import com.dmall.pms.api.dto.skuattributevalue.request.SaveSkuAttributeValueRequestDTO;
import com.dmall.pms.api.dto.skuattributevalue.request.UpdateSkuAttributeValueRequestDTO;
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
 * @author: created by hang.yu on 2019-12-22 15:09:34
 */
@Api(tags = "sku属性值服务")
@RequestMapping("/skuAttributeValue")
public interface SkuAttributeValueService {

    @PostMapping("/")
    @ApiOperation(value = "新增sku属性值")
    BaseResult<Long> save(@Valid @RequestBody SaveSkuAttributeValueRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku属性值")
    @ApiImplicitParam(name = "id", value = "sku属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku属性值")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuAttributeValueRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku属性值")
    @ApiImplicitParam(name = "id", value = "sku属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonSkuAttributeValueResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "sku属性值列表")
    BaseResult<List<CommonSkuAttributeValueResponseDTO>> list(@RequestBody ListSkuAttributeValueRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku属性值分页")
    BaseResult<LayuiPage<CommonSkuAttributeValueResponseDTO>> page(@RequestBody PageSkuAttributeValueRequestDTO requestDTO);

}

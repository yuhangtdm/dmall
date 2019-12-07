package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.productattribute.request.ListProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.request.PageProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.common.CommonProductAttributeResponseDTO;
import com.dmall.pms.api.dto.productattribute.request.SaveProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.request.UpdateProductAttributeRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性值服务
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Api(tags = "属性值服务")
@RequestMapping("/productAttribute")
public interface ProductAttributeService {

    @PostMapping("/")
    @ApiOperation(value = "新增属性值")
    BaseResult<Long> save(@Valid @RequestBody SaveProductAttributeRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除属性值")
    @ApiImplicitParam(name = "id", value = "属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改属性值")
    BaseResult<Long> update(@Valid @RequestBody UpdateProductAttributeRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询属性值")
    @ApiImplicitParam(name = "id", value = "属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonProductAttributeResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "属性值列表")
    BaseResult<List<CommonProductAttributeResponseDTO>> list(@RequestBody ListProductAttributeRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "属性值分页")
    BaseResult<LayuiPage<CommonProductAttributeResponseDTO>> page(@RequestBody PageProductAttributeRequestDTO requestDTO);

}

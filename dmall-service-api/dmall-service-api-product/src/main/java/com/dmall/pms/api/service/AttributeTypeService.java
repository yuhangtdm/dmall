package com.dmall.pms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pms.api.dto.attributetype.request.*;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性类别服务
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Api(tags = "属性类别服务")
@RequestMapping("/attributeType")
public interface AttributeTypeService {

    @PostMapping
    @ApiOperation(value = "新增属性类别")
    BaseResult<Long> save(@Valid @RequestBody SaveAttributeTypeRequestDTO requestDTO);

    @PostMapping
    @ApiOperation(value = "批量新增属性类别")
    BaseResult<Long> batchSave(@Valid @RequestBody BatchSaveAttributeTypeRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除属性类别")
    @ApiImplicitParam(name = "id", value = "属性类别id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改属性类别")
    BaseResult<Long> update(@Valid @RequestBody UpdateAttributeTypeRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询属性类别")
    @ApiImplicitParam(name = "id", value = "属性类别id", required = true, dataType = "int", paramType = "path")
    BaseResult<AttributeTypeResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "属性类别列表")
    BaseResult<List<AttributeTypeResponseDTO>> list(@Valid @RequestBody ListAttributeTypeRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "属性类别分页")
    BaseResult<ResponsePage<AttributeTypeResponseDTO>> page(@Valid @RequestBody PageAttributeTypeRequestDTO requestDTO);

}

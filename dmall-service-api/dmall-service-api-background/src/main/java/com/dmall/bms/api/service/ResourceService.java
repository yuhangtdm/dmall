package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.resource.request.ListResourceRequestDTO;
import com.dmall.bms.api.dto.resource.request.PageResourceRequestDTO;
import com.dmall.bms.api.dto.resource.common.CommonResourceResponseDTO;
import com.dmall.bms.api.dto.resource.request.SaveResourceRequestDTO;
import com.dmall.bms.api.dto.resource.request.UpdateResourceRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 资源服务
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Api(tags = "资源服务")
@RequestMapping("/resource")
public interface ResourceService {

    @PostMapping("/")
    @ApiOperation(value = "新增资源")
    BaseResult<Long> save(@Valid @RequestBody SaveResourceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改资源")
    BaseResult<Long> update(@Valid @RequestBody UpdateResourceRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询资源")
    @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonResourceResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "资源列表")
    BaseResult<List<CommonResourceResponseDTO>> list(@RequestBody ListResourceRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "资源分页")
    BaseResult<LayuiPage<CommonResourceResponseDTO>> page(@RequestBody PageResourceRequestDTO requestDTO);

}

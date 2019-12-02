package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.sku.request.ListSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.api.dto.sku.request.SaveSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.UpdateSkuRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "sku服务")
@RequestMapping("/sku")
public interface SkuService {

    @PostMapping("/")
    @ApiOperation(value = "新增sku")
    BaseResult<Long> save(@Valid @RequestBody SaveSkuRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku")
    @ApiImplicitParam(name = "id", value = "skuid", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku")
    @ApiImplicitParam(name = "id", value = "skuid", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonSkuResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "sku列表")
    BaseResult<List<CommonSkuResponseDTO>> list(@RequestBody ListSkuRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku分页")
    BaseResult<LayuiPage<CommonSkuResponseDTO>> page(@RequestBody PageSkuRequestDTO requestDTO);

}

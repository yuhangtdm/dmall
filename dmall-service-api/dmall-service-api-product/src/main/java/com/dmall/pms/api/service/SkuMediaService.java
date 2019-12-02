package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.skumedia.request.ListSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.request.PageSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaResponseDTO;
import com.dmall.pms.api.dto.skumedia.request.SaveSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.request.UpdateSkuMediaRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku媒体对象服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "sku媒体对象服务")
@RequestMapping("/skuMedia")
public interface SkuMediaService {

    @PostMapping("/")
    @ApiOperation(value = "新增sku媒体对象")
    BaseResult<Long> save(@Valid @RequestBody SaveSkuMediaRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku媒体对象")
    @ApiImplicitParam(name = "id", value = "sku媒体对象id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku媒体对象")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuMediaRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku媒体对象")
    @ApiImplicitParam(name = "id", value = "sku媒体对象id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonSkuMediaResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "sku媒体对象列表")
    BaseResult<List<CommonSkuMediaResponseDTO>> list(@RequestBody ListSkuMediaRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku媒体对象分页")
    BaseResult<LayuiPage<CommonSkuMediaResponseDTO>> page(@RequestBody PageSkuMediaRequestDTO requestDTO);

}

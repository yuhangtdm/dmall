package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.skuext.request.ListSkuExtRequestDTO;
import com.dmall.pms.api.dto.skuext.request.PageSkuExtRequestDTO;
import com.dmall.pms.api.dto.skuext.common.CommonSkuExtResponseDTO;
import com.dmall.pms.api.dto.skuext.request.SaveSkuExtRequestDTO;
import com.dmall.pms.api.dto.skuext.request.UpdateSkuExtRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku扩展服务
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Api(tags = "sku扩展服务")
@RequestMapping("/skuExt")
public interface SkuExtService {

    @PostMapping("/")
    @ApiOperation(value = "新增sku扩展")
    BaseResult<Long> save(@Valid @RequestBody SaveSkuExtRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku扩展")
    @ApiImplicitParam(name = "id", value = "sku扩展id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku扩展")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuExtRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku扩展")
    @ApiImplicitParam(name = "id", value = "sku扩展id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonSkuExtResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "sku扩展列表")
    BaseResult<List<CommonSkuExtResponseDTO>> list(@RequestBody ListSkuExtRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku扩展分页")
    BaseResult<LayuiPage<CommonSkuExtResponseDTO>> page(@RequestBody PageSkuExtRequestDTO requestDTO);

}

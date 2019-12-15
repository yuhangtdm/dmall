package com.dmall.pms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.product.common.CommonProductResponseDTO;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @description: 商品服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "商品服务")
@RequestMapping("/product")
public interface ProductService {

    @PostMapping("/")
    @ApiOperation(value = "新增商品")
    BaseResult<Long> save(@Valid @RequestBody SaveProductRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "商品分页")
    BaseResult<LayuiPage<PageProductResponseDTO>> page(@RequestBody PageProductRequestDTO requestDTO);

    @PutMapping("/")
    @ApiOperation(value = "修改商品")
    BaseResult<Long> update(@Valid @RequestBody UpdateProductRequestDTO requestDTO);

    /**
     * TODO sku完成之后再进行开发
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonProductResponseDTO> get(@PathVariable("id") Long id);



}

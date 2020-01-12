package com.dmall.pms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import com.dmall.common.dto.UploadResult;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 商品服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "商品服务")
@Validated
@RequestMapping("/product")
public interface ProductService {

    @PostMapping
    @ApiOperation(value = "新增商品")
    BaseResult<Long> save(@Valid @RequestBody SaveProductRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改商品")
    BaseResult<Long> update(@Valid @RequestBody UpdateProductRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "int", paramType = "path")
    BaseResult<GetProductResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/page")
    @ApiOperation(value = "商品分页")
    BaseResult<LayUiPage<PageProductResponseDTO>> page(@Valid @RequestBody PageProductRequestDTO requestDTO);

    @ApiOperation(value = "上传商品主图")
    @PostMapping("/uploadProductPic")
    BaseResult<UploadResult> uploadProductPic(@NotNull(message = "商品主图不能为空") MultipartFile file);

    @ApiOperation(value = "上传商品规格配图")
    @PostMapping("/uploadProductAttributePic")
    BaseResult<UploadResult> uploadProductAttributePic(@NotNull(message = "商品规格配图不能为空") MultipartFile file);

}

package com.dmall.pms.api.service;

import com.dmall.common.model.result.UploadResult;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuExtRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuMediaRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.update.UpdateSkuRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: sku服务
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Api(tags = "sku服务")
@Validated
@RequestMapping("/sku")
public interface SkuService {

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "新增或修改sku基本信息,返回skuId")
    BaseResult<Long> saveOrUpdate(@Valid @RequestBody SaveSkuRequestDTO requestDTO);

    @PostMapping("/saveSkuAttribute")
    @ApiOperation(value = "保存sku属性信息,返回skuId")
    BaseResult<Long> saveSkuAttributeValue(@Valid @RequestBody SaveSkuAttributeRequestDTO requestDTO);

    @PostMapping("/saveSkuExt")
    @ApiOperation(value = "保存sku扩展信息,返回skuId")
    BaseResult<Long> saveSkuExt(@Valid @RequestBody SaveSkuExtRequestDTO requestDTO);

    @PostMapping("/upload/{id}")
    @ApiOperation(value = "上传sku图片")
    @ApiImplicitParam(name = "id", value = "skuId", required = true, dataType = "int", paramType = "path")
    BaseResult<UploadResult> upload(@PathVariable("id") Long id,@NotNull(message = "sku图片不能为空")  MultipartFile file);

    @PostMapping("/saveSkuMedia")
    @ApiOperation(value = "保存sku图片信息,返回skuId")
    BaseResult<Long> saveSkuMedia(@Valid @RequestBody SaveSkuMediaRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除sku")
    @ApiImplicitParam(name = "id", value = "skuId", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改sku")
    BaseResult<Long> update(@Valid @RequestBody UpdateSkuRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询sku")
    @ApiImplicitParam(name = "id", value = "skuId", required = true, dataType = "int", paramType = "path")
    BaseResult<GetSkuResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/page")
    @ApiOperation(value = "sku分页")
    BaseResult<LayuiPage<PageSkuResponseDTO>> page(@RequestBody PageSkuRequestDTO requestDTO);

}

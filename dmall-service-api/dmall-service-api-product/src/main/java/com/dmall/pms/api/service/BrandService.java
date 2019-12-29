package com.dmall.pms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.common.model.result.UploadResult;
import com.dmall.pms.api.dto.brand.common.CommonBrandResponseDTO;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.PageBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.SaveBrandRequestDTO;
import com.dmall.pms.api.dto.brand.request.UpdateBrandRequestDTO;
import com.dmall.pms.api.dto.brand.response.PageBrandResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 品牌服务
 * @author: created by hang.yu on 2019/11/19 22:46
 */
@Api(tags = "品牌管理")
@RequestMapping("/brand")
public interface BrandService {

    @ApiOperation(value = "新增品牌")
    @PostMapping("/")
    BaseResult<Long> save(@Valid @RequestBody SaveBrandRequestDTO requestDTO);

    @ApiOperation(value = "删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @ApiOperation(value = "修改品牌")
    @PutMapping("/")
    BaseResult<Long> update(@Valid @RequestBody UpdateBrandRequestDTO requestDTO);

    @ApiOperation(value = "根据id查询品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    BaseResult<CommonBrandResponseDTO> get(@PathVariable("id") Long id);

    @ApiOperation(value = "品牌列表")
    @PostMapping("/list")
    BaseResult<List<CommonBrandResponseDTO>> list(@Valid @RequestBody ListBrandRequestDTO pageRequestDTO);

    @ApiOperation(value = "品牌分页")
    @PostMapping("/page")
    BaseResult<LayuiPage<PageBrandResponseDTO>> page(@Valid @RequestBody PageBrandRequestDTO pageRequestDTO);

    @ApiOperation(value = "上传品牌logo")
    @PostMapping("/uploadLogo")
    BaseResult<UploadResult> uploadLogo(MultipartFile file);
}

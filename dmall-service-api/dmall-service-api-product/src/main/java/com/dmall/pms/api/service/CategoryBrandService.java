package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.categorybrand.request.ListCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.request.PageCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandResponseDTO;
import com.dmall.pms.api.dto.categorybrand.request.SaveCategoryBrandRequestDTO;
import com.dmall.pms.api.dto.categorybrand.request.UpdateCategoryBrandRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 分类品牌关系服务
 * @author: created by hang.yu on 2019-12-07 20:44:53
 */
@Api(tags = "分类品牌关系服务")
@RequestMapping("/categoryBrand")
public interface CategoryBrandService {

    @PostMapping("/")
    @ApiOperation(value = "新增分类品牌关系")
    BaseResult<Long> save(@Valid @RequestBody SaveCategoryBrandRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除分类品牌关系")
    @ApiImplicitParam(name = "id", value = "分类品牌关系id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改分类品牌关系")
    BaseResult<Long> update(@Valid @RequestBody UpdateCategoryBrandRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询分类品牌关系")
    @ApiImplicitParam(name = "id", value = "分类品牌关系id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCategoryBrandResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "分类品牌关系列表")
    BaseResult<List<CommonCategoryBrandResponseDTO>> list(@RequestBody ListCategoryBrandRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "分类品牌关系分页")
    BaseResult<LayuiPage<CommonCategoryBrandResponseDTO>> page(@RequestBody PageCategoryBrandRequestDTO requestDTO);

}

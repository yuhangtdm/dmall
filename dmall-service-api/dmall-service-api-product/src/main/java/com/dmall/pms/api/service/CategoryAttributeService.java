package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.categoryattribute.request.ListCategoryAttributeRequestDTO;
import com.dmall.pms.api.dto.categoryattribute.request.PageCategoryAttributeRequestDTO;
import com.dmall.pms.api.dto.categoryattribute.common.CommonCategoryAttributeResponseDTO;
import com.dmall.pms.api.dto.categoryattribute.request.SaveCategoryAttributeRequestDTO;
import com.dmall.pms.api.dto.categoryattribute.request.UpdateCategoryAttributeRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 分类属性表 商品分类和商品属性的中间服务
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Api(tags = "分类属性表 商品分类和商品属性的中间服务")
@RequestMapping("/categoryAttribute")
public interface CategoryAttributeService {

    @PostMapping("/")
    @ApiOperation(value = "新增分类属性表 商品分类和商品属性的中间")
    BaseResult<Long> save(@Valid @RequestBody SaveCategoryAttributeRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除分类属性表 商品分类和商品属性的中间")
    @ApiImplicitParam(name = "id", value = "分类属性表 商品分类和商品属性的中间id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改分类属性表 商品分类和商品属性的中间")
    BaseResult<Long> update(@Valid @RequestBody UpdateCategoryAttributeRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询分类属性表 商品分类和商品属性的中间")
    @ApiImplicitParam(name = "id", value = "分类属性表 商品分类和商品属性的中间id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCategoryAttributeResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "分类属性表 商品分类和商品属性的中间列表")
    BaseResult<List<CommonCategoryAttributeResponseDTO>> list(@RequestBody ListCategoryAttributeRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "分类属性表 商品分类和商品属性的中间分页")
    BaseResult<LayuiPage<CommonCategoryAttributeResponseDTO>> page(@RequestBody PageCategoryAttributeRequestDTO requestDTO);

}

package com.dmall.pms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.pms.api.dto.category.common.CategoryResponseDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.api.dto.category.response.ZTreeCategoryResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品分类服务
 * @author: created by hang.yu on 2019/11/24 11:59
 */
@Api(tags = "分类管理")
@RequestMapping("/category")
public interface CategoryService {

    @ApiOperation(value = "新增分类")
    @PostMapping("/")
    BaseResult<Long> save(@Valid @RequestBody SaveCategoryRequestDTO requestDTO);

    @ApiOperation(value = "修改分类")
    @PutMapping("/")
    BaseResult<Long> update(@Valid @RequestBody UpdateCategoryRequestDTO requestDTO);

    @ApiOperation(value = "分类列表")
    @PostMapping("/list")
    BaseResult<List<CategoryResponseDTO>> list(@RequestBody ListCategoryRequestDTO requestDTO);

    @ApiOperation(value = "删除分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @ApiOperation(value = "根据id查询分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    BaseResult<CategoryResponseDTO> get(@PathVariable("id") Long id);

    @ApiOperation(value = "分类zTree树")
    @ApiImplicitParam(name = "parentId", value = "上级id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/zTree/{parentId}")
    BaseResult<List<ZTreeCategoryResponseDTO>> zTree(@PathVariable("parentId") Long parentId);
}

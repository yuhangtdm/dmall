package com.dmall.pms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.PageAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.PageAttributeTypeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性分类服务
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Api(tags = "属性分类服务" )
@RequestMapping("/attributeType" )
public interface AttributeTypeService {

    @PostMapping("/" )
    @ApiOperation(value = "新增属性分类" )
    BaseResult<Long> save(@Valid @RequestBody SaveAttributeTypeRequestDTO requestDTO);

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "删除属性分类" )
    @ApiImplicitParam(name = "id" , value = "属性分类id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<Long> delete(@PathVariable("id" ) Long id);

    @PutMapping("/" )
    @ApiOperation(value = "修改属性分类" )
    BaseResult<Long> update(@Valid @RequestBody UpdateAttributeTypeRequestDTO requestDTO);

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据id查询属性分类" )
    @ApiImplicitParam(name = "id" , value = "属性分类id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<CommonAttributeTypeResponseDTO> get(@PathVariable("id" ) Long id);

    @PostMapping("/list" )
    @ApiOperation(value = "属性分类列表" )
    BaseResult<List<CommonAttributeTypeResponseDTO>> list(@Valid @RequestBody ListAttributeTypeRequestDTO requestDTO);

    @PostMapping("/page" )
    @ApiOperation(value = "属性分类分页" )
    BaseResult<LayuiPage<PageAttributeTypeResponseDTO>> page(@Valid @RequestBody PageAttributeTypeRequestDTO requestDTO);

}

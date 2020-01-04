package com.dmall.pms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.PageAttributeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性服务
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Api(tags = "属性服务" )
@RequestMapping("/attribute" )
public interface AttributeService {

    @PostMapping("/" )
    @ApiOperation(value = "新增属性" )
    BaseResult<Long> save(@Valid @RequestBody SaveAttributeRequestDTO requestDTO);

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "删除属性" )
    @ApiImplicitParam(name = "id" , value = "属性id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<Long> delete(@PathVariable("id" ) Long id);

    @PutMapping("/" )
    @ApiOperation(value = "修改属性" )
    BaseResult<Long> update(@Valid @RequestBody UpdateAttributeRequestDTO requestDTO);

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据id查询属性" )
    @ApiImplicitParam(name = "id" , value = "属性id" , required = true, dataType = "int" , paramType = "path" )
    BaseResult<CommonAttributeResponseDTO> get(@PathVariable("id" ) Long id);

    @PostMapping("/list" )
    @ApiOperation(value = "属性列表" )
    BaseResult<List<CommonAttributeResponseDTO>> list(@Valid @RequestBody ListAttributeRequestDTO requestDTO);

    @PostMapping("/page" )
    @ApiOperation(value = "属性分页" )
    BaseResult<LayuiPage<PageAttributeResponseDTO>> page(@Valid @RequestBody PageAttributeRequestDTO requestDTO);

}

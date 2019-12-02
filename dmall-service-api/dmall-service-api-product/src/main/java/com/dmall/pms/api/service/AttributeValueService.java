package com.dmall.pms.api.service;

import com.dmall.pms.api.dto.attributevalue.request.ListAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.request.PageAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueResponseDTO;
import com.dmall.pms.api.dto.attributevalue.request.SaveAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.request.UpdateAttributeValueRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性值服务
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Api(tags = "属性值服务")
@RequestMapping("/attributeValue")
public interface AttributeValueService {

    @PostMapping("/")
    @ApiOperation(value = "新增属性值")
    BaseResult<Long> save(@Valid @RequestBody SaveAttributeValueRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除属性值")
    @ApiImplicitParam(name = "id", value = "属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改属性值")
    BaseResult<Long> update(@Valid @RequestBody UpdateAttributeValueRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询属性值")
    @ApiImplicitParam(name = "id", value = "属性值id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonAttributeValueResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "属性值列表")
    BaseResult<List<CommonAttributeValueResponseDTO>> list(@RequestBody ListAttributeValueRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "属性值分页")
    BaseResult<LayuiPage<CommonAttributeValueResponseDTO>> page(@RequestBody PageAttributeValueRequestDTO requestDTO);

}

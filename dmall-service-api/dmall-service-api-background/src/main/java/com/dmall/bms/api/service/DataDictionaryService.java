package com.dmall.bms.api.service;

import com.dmall.bms.api.dto.datadictionary.request.ListDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.request.PageDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.common.CommonDataDictionaryResponseDTO;
import com.dmall.bms.api.dto.datadictionary.request.SaveDataDictionaryRequestDTO;
import com.dmall.bms.api.dto.datadictionary.request.UpdateDataDictionaryRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 数据字典服务
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Api(tags = "数据字典服务")
@RequestMapping("/dataDictionary")
public interface DataDictionaryService {

    @PostMapping("/")
    @ApiOperation(value = "新增数据字典")
    BaseResult<Long> save(@Valid @RequestBody SaveDataDictionaryRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除数据字典")
    @ApiImplicitParam(name = "id", value = "数据字典id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改数据字典")
    BaseResult<Long> update(@Valid @RequestBody UpdateDataDictionaryRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询数据字典")
    @ApiImplicitParam(name = "id", value = "数据字典id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonDataDictionaryResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "数据字典列表")
    BaseResult<List<CommonDataDictionaryResponseDTO>> list(@RequestBody ListDataDictionaryRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "数据字典分页")
    BaseResult<LayuiPage<CommonDataDictionaryResponseDTO>> page(@RequestBody PageDataDictionaryRequestDTO requestDTO);

}

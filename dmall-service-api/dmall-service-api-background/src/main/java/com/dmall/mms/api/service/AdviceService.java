package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.advice.request.ListAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.request.PageAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.common.CommonAdviceResponseDTO;
import com.dmall.mms.api.dto.advice.request.SaveAdviceRequestDTO;
import com.dmall.mms.api.dto.advice.request.UpdateAdviceRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员意见表 服务
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Api(tags = "会员意见表 服务")
@RequestMapping("/advice")
public interface AdviceService {

    @PostMapping
    @ApiOperation(value = "新增会员意见表 ")
    BaseResult<Long> save(@Valid @RequestBody SaveAdviceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员意见表 ")
    @ApiImplicitParam(name = "id", value = "会员意见表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员意见表 ")
    BaseResult<Long> update(@Valid @RequestBody UpdateAdviceRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员意见表 ")
    @ApiImplicitParam(name = "id", value = "会员意见表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonAdviceResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员意见表 列表")
    BaseResult<List<CommonAdviceResponseDTO>> list(@RequestBody ListAdviceRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员意见表 分页")
    BaseResult<LayUiPage<CommonAdviceResponseDTO>> page(@RequestBody PageAdviceRequestDTO requestDTO);

}

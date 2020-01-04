package com.dmall.mms.api.service;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.dto.memberviewsku.request.ListMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.PageMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.SaveMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.UpdateMemberViewSkuRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员浏览历史记录服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员浏览历史记录服务")
@RequestMapping("/memberViewSku")
public interface MemberViewSkuService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员浏览历史记录")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberViewSkuRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员浏览历史记录")
    @ApiImplicitParam(name = "id", value = "会员浏览历史记录id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员浏览历史记录")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberViewSkuRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员浏览历史记录")
    @ApiImplicitParam(name = "id", value = "会员浏览历史记录id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberViewSkuResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员浏览历史记录列表")
    BaseResult<List<CommonMemberViewSkuResponseDTO>> list(@RequestBody ListMemberViewSkuRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员浏览历史记录分页")
    BaseResult<LayuiPage<CommonMemberViewSkuResponseDTO>> page(@RequestBody PageMemberViewSkuRequestDTO requestDTO);

}

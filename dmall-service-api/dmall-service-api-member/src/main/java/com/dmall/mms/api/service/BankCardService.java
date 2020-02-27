package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.bankcard.request.ListBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.request.PageBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.common.CommonBankCardResponseDTO;
import com.dmall.mms.api.dto.bankcard.request.SaveBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.request.UpdateBankCardRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员银行卡服务
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Api(tags = "会员银行卡服务")
@RequestMapping("/bankCard")
public interface BankCardService {

    @PostMapping
    @ApiOperation(value = "新增会员银行卡")
    BaseResult<Long> save(@Valid @RequestBody SaveBankCardRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员银行卡")
    @ApiImplicitParam(name = "id", value = "会员银行卡id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员银行卡")
    BaseResult<Long> update(@Valid @RequestBody UpdateBankCardRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员银行卡")
    @ApiImplicitParam(name = "id", value = "会员银行卡id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonBankCardResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员银行卡列表")
    BaseResult<List<CommonBankCardResponseDTO>> list(@RequestBody ListBankCardRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员银行卡分页")
    BaseResult<LayUiPage<CommonBankCardResponseDTO>> page(@RequestBody PageBankCardRequestDTO requestDTO);

}

package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.memberinvoice.MemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.UpdateMemberInvoiceRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 会员发票服务
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Api(tags = "会员发票服务")
@RequestMapping("/memberInvoice")
public interface MemberInvoiceService {

    @PostMapping
    @ApiOperation(value = "新增会员发票")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberInvoiceRequestDTO requestDTO);

    @PutMapping
    @ApiOperation(value = "修改会员发票")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberInvoiceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员发票")
    @ApiImplicitParam(name = "id", value = "会员发票id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员发票")
    @ApiImplicitParam(name = "id", value = "会员发票id", required = true, dataType = "int", paramType = "path")
    BaseResult<MemberInvoiceResponseDTO> get(@PathVariable("id") Long id);

    @GetMapping("/{memberId}")
    @ApiOperation(value = "根据会员id查询会员发票")
    @ApiImplicitParam(name = "memberId", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<MemberInvoiceResponseDTO> getByMemberId(@PathVariable("memberId") Long memberId);

}

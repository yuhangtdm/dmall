package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.memberinvoice.request.ListMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.request.PageMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.request.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.request.UpdateMemberInvoiceRequestDTO;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员发票服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员发票服务")
@RequestMapping("/memberInvoice")
public interface MemberInvoiceService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员发票")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberInvoiceRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员发票")
    @ApiImplicitParam(name = "id", value = "会员发票id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员发票")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberInvoiceRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员发票")
    @ApiImplicitParam(name = "id", value = "会员发票id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberInvoiceResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员发票列表")
    BaseResult<List<CommonMemberInvoiceResponseDTO>> list(@RequestBody ListMemberInvoiceRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员发票分页")
    BaseResult<LayuiPage<CommonMemberInvoiceResponseDTO>> page(@RequestBody PageMemberInvoiceRequestDTO requestDTO);

}

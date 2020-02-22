package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.memberreceiveaddress.request.ListMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.PageMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressResponseDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.SaveMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收货地址服务
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Api(tags = "会员收货地址服务")
@RequestMapping("/memberReceiveAddress")
public interface MemberReceiveAddressService {

    @PostMapping
    @ApiOperation(value = "新增会员收货地址")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberReceiveAddressRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员收货地址")
    @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员收货地址")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberReceiveAddressRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员收货地址")
    @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberReceiveAddressResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员收货地址列表")
    BaseResult<List<CommonMemberReceiveAddressResponseDTO>> list(@RequestBody ListMemberReceiveAddressRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员收货地址分页")
    BaseResult<LayUiPage<CommonMemberReceiveAddressResponseDTO>> page(@RequestBody PageMemberReceiveAddressRequestDTO requestDTO);

}

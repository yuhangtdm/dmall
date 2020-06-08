package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.memberreceiveaddress.request.SaveMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ReceiveAddressResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收货地址服务
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Api(tags = "会员收货地址服务")
@RequestMapping("/memberReceiveAddress")
public interface MemberReceiveAddressService {

    @GetMapping("/list")
    @ApiOperation(value = "会员收货地址列表")
    BaseResult<List<ReceiveAddressResponseDTO>> list();

    @PostMapping
    @ApiOperation(value = "新增会员收货地址")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberReceiveAddressRequestDTO requestDTO);

    @PutMapping
    @ApiOperation(value = "修改会员收货地址")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberReceiveAddressRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员收货地址")
    @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @GetMapping("/setDefault/{id}")
    @ApiOperation(value = "设为默认地址")
    @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> setDefault(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员收货地址")
    @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "int", paramType = "path")
    BaseResult<ReceiveAddressResponseDTO> get(@PathVariable("id") Long id);

}

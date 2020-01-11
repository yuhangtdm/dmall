package com.dmall.mms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import com.dmall.mms.api.dto.membercouponmember.common.CommonMemberCouponMemberResponseDTO;
import com.dmall.mms.api.dto.membercouponmember.request.ListMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.PageMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.SaveMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.UpdateMemberCouponMemberRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员-优惠券服务
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Api(tags = "会员-优惠券服务")
@RequestMapping("/memberCouponMember")
public interface MemberCouponMemberService {

    @PostMapping("/")
    @ApiOperation(value = "新增会员-优惠券")
    BaseResult<Long> save(@Valid @RequestBody SaveMemberCouponMemberRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员-优惠券")
    @ApiImplicitParam(name = "id", value = "会员-优惠券id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping("/")
    @ApiOperation(value = "修改会员-优惠券")
    BaseResult<Long> update(@Valid @RequestBody UpdateMemberCouponMemberRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员-优惠券")
    @ApiImplicitParam(name = "id", value = "会员-优惠券id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonMemberCouponMemberResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员-优惠券列表")
    BaseResult<List<CommonMemberCouponMemberResponseDTO>> list(@RequestBody ListMemberCouponMemberRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员-优惠券分页")
    BaseResult<LayUiPage<CommonMemberCouponMemberResponseDTO>> page(@RequestBody PageMemberCouponMemberRequestDTO requestDTO);

}

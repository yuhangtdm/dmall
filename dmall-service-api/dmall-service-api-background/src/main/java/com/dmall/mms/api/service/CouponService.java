package com.dmall.mms.api.service;

import com.dmall.mms.api.dto.coupon.request.ListCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.request.PageCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.common.CommonCouponResponseDTO;
import com.dmall.mms.api.dto.coupon.request.SaveCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.request.UpdateCouponRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员优惠券表 服务
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Api(tags = "会员优惠券表 服务")
@RequestMapping("/coupon")
public interface CouponService {

    @PostMapping
    @ApiOperation(value = "新增会员优惠券表 ")
    BaseResult<Long> save(@Valid @RequestBody SaveCouponRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员优惠券表 ")
    @ApiImplicitParam(name = "id", value = "会员优惠券表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<Long> delete(@PathVariable("id") Long id);

    @PutMapping
    @ApiOperation(value = "修改会员优惠券表 ")
    BaseResult<Long> update(@Valid @RequestBody UpdateCouponRequestDTO requestDTO);

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询会员优惠券表 ")
    @ApiImplicitParam(name = "id", value = "会员优惠券表 id", required = true, dataType = "int", paramType = "path")
    BaseResult<CommonCouponResponseDTO> get(@PathVariable("id") Long id);

    @PostMapping("/list")
    @ApiOperation(value = "会员优惠券表 列表")
    BaseResult<List<CommonCouponResponseDTO>> list(@RequestBody ListCouponRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "会员优惠券表 分页")
    BaseResult<LayUiPage<CommonCouponResponseDTO>> page(@RequestBody PageCouponRequestDTO requestDTO);

}

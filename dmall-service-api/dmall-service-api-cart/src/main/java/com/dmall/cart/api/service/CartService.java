package com.dmall.cart.api.service;

import com.dmall.cart.api.dto.add.AddCartRequestDTO;
import com.dmall.cart.api.dto.add.AddCartResponseDTO;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.select.SelectCartRequestDTO;
import com.dmall.cart.api.dto.update.UpdateNumberRequestDTO;
import com.dmall.common.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @description: 购物车服务
 * @author: created by hang.yu on 2020-03-12 23:12:09
 */
@Api(tags = "购物车服务")
@RequestMapping("/cart")
public interface CartService {

    @PostMapping("/add")
    @ApiOperation(value = "新增购物车")
    BaseResult<AddCartResponseDTO> add(@RequestBody @Valid AddCartRequestDTO requestDTO);

    @GetMapping("/list")
    @ApiOperation(value = "购物车列表")
    BaseResult<CartListResponseDTO> list();

    @PostMapping("/updateNumber")
    @ApiOperation(value = "修改数量")
    BaseResult<CartListResponseDTO> updateNumber(@RequestBody @Valid UpdateNumberRequestDTO requestDTO);

    @PostMapping("/select")
    @ApiOperation(value = "勾选购物车")
    BaseResult<CartListResponseDTO> select(@RequestBody @Valid SelectCartRequestDTO requestDTO);

    @PostMapping("/delete")
    @ApiOperation(value = "删除选中的购物车")
    BaseResult<CartListResponseDTO> delete(@RequestBody @Valid DeleteCartRequestDTO requestDTO);

    @GetMapping("/clearInvalid")
    @ApiOperation(value = "清空下架的商品")
    BaseResult<CartListResponseDTO> clearInvalid();

}

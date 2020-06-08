package com.dmall.cart.service.impl;

import com.dmall.cart.api.dto.add.AddCartRequestDTO;
import com.dmall.cart.api.dto.add.AddCartResponseDTO;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.cart.api.dto.select.SelectCartRequestDTO;
import com.dmall.cart.api.dto.update.UpdateNumberRequestDTO;
import com.dmall.cart.api.service.CartService;
import com.dmall.cart.service.impl.handler.*;
import com.dmall.common.dto.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 购物车服务实现
 * @author: created by hang.yu on 2020-03-11 22:46:56
 */
@RestController
public class CartServiceImpl implements CartService {

    @Autowired
    private AddCartHandler addCartHandler;

    @Autowired
    private ListCartHandler listCartHandler;

    @Autowired
    private UpdateNumberHandler updateNumberHandler;

    @Autowired
    private SelectCartHandler selectCartHandler;

    @Autowired
    private DeleteCartHandler deleteCartHandler;

    @Autowired
    private ClearInvalidHandler clearInvalidHandler;

    @Override
    public BaseResult<AddCartResponseDTO> add(@RequestBody AddCartRequestDTO requestDTO) {
        return addCartHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CartListResponseDTO> list() {
        return listCartHandler.handler(null);
    }

    @Override
    public BaseResult<CartListResponseDTO> updateNumber(@RequestBody UpdateNumberRequestDTO requestDTO) {
        return updateNumberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CartListResponseDTO> select(@RequestBody SelectCartRequestDTO requestDTO) {
        return selectCartHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CartListResponseDTO> delete(@RequestBody DeleteCartRequestDTO requestDTO) {
        return deleteCartHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CartListResponseDTO> clearInvalid() {
        return clearInvalidHandler.handler(null);
    }

}

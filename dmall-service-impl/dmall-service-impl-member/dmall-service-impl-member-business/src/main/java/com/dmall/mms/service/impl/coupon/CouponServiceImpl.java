package com.dmall.mms.service.impl.coupon;

import com.dmall.mms.api.dto.coupon.request.SaveCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.request.UpdateCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.request.ListCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.request.PageCouponRequestDTO;
import com.dmall.mms.api.dto.coupon.common.CommonCouponResponseDTO;
import com.dmall.mms.api.service.CouponService;
import com.dmall.mms.service.impl.coupon.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 会员优惠券表 服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class CouponServiceImpl implements CouponService {

    @Autowired
    private SaveCouponHandler saveCouponHandler;

    @Autowired
    private DeleteCouponHandler deleteCouponHandler;

    @Autowired
    private UpdateCouponHandler updateCouponHandler;

    @Autowired
    private GetCouponHandler getCouponHandler;

    @Autowired
    private ListCouponHandler listCouponHandler;

    @Autowired
    private PageCouponHandler pageCouponHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveCouponRequestDTO requestDTO) {
        return saveCouponHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteCouponHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateCouponRequestDTO requestDTO) {
        return updateCouponHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonCouponResponseDTO> get(Long id) {
        return getCouponHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonCouponResponseDTO>> list(@RequestBody ListCouponRequestDTO requestDTO) {
        return listCouponHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonCouponResponseDTO>> page(@RequestBody PageCouponRequestDTO requestDTO) {
        return pageCouponHandler.handler(requestDTO);
    }

}

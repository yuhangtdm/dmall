package com.dmall.mms.service.impl.coupon.handler;

import com.dmall.mms.api.dto.coupon.request.UpdateCouponRequestDTO;
import com.dmall.mms.service.impl.coupon.enums.CouponErrorEnum;
import com.dmall.mms.generator.dataobject.CouponDO;
import com.dmall.mms.generator.mapper.CouponMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员优惠券表 处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class UpdateCouponHandler extends AbstractCommonHandler<UpdateCouponRequestDTO, CouponDO, Long> {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public BaseResult<Long> validate(UpdateCouponRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateCouponRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
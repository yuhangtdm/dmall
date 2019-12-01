package com.dmall.mms.service.impl.membercouponmember.handler;

import com.dmall.mms.service.impl.membercouponmember.enums.MemberCouponMemberErrorEnum;
import com.dmall.mms.generator.dataobject.MemberCouponMemberDO;
import com.dmall.mms.generator.mapper.MemberCouponMemberMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除会员-优惠券处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class DeleteMemberCouponMemberHandler extends AbstractCommonHandler<Long, MemberCouponMemberDO, Long> {

    @Autowired
    private MemberCouponMemberMapper memberCouponMemberMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}

package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.mms.service.impl.memberreceiveaddress.enums.MemberReceiveAddressErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员收货地址处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class UpdateMemberReceiveAddressHandler extends AbstractCommonHandler<UpdateMemberReceiveAddressRequestDTO, MemberReceiveAddressDO, Long> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

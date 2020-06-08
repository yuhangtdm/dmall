package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员收货地址处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class UpdateMemberReceiveAddressHandler
    extends AbstractCommonHandler<UpdateMemberReceiveAddressRequestDTO, MemberReceiveAddressDO, Long> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<Long> processor(UpdateMemberReceiveAddressRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        MemberReceiveAddressDO memberReceiveAddressDO = memberReceiveAddressMapper.selectById(requestDTO.getId());
        if (memberReceiveAddressDO == null) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_NOT_EXIST);
        }
        // 判断只有当前会员才能修改
        if (!loginMember.getId().equals(memberReceiveAddressDO.getCreator())) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR);
        }
        MemberReceiveAddressDO addressDO = dtoConvertDo(requestDTO, MemberReceiveAddressDO.class);
        memberReceiveAddressMapper.updateById(addressDO);
        return ResultUtil.success();
    }

}
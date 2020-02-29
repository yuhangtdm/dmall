package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.mms.api.enums.MemberReceiveAddressErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除会员收货地址处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class DeleteMemberReceiveAddressHandler extends AbstractCommonHandler<Long, MemberReceiveAddressDO, Long> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<Long> processor(Long id) {
        MemberReceiveAddressDO memberReceiveAddressDO = memberReceiveAddressMapper.selectById(id);
        if (memberReceiveAddressDO == null){
            return ResultUtil.fail(MemberReceiveAddressErrorEnum.MEMBER_RECEIVE_ADDRESS_NOT_EXIST);
        }
        // 判断只有当前会员才能修改
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        if (!loginMember.getId().equals(memberReceiveAddressDO.getCreator())){
            return ResultUtil.fail(MemberReceiveAddressErrorEnum.MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR);
        }
        memberReceiveAddressMapper.deleteById(id);
        return ResultUtil.success();
    }

}

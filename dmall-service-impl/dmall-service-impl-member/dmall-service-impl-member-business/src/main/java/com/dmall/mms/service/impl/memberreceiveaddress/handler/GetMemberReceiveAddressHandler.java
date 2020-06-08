package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ReceiveAddressResponseDTO;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询会员收货地址处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class GetMemberReceiveAddressHandler
    extends AbstractCommonHandler<Long, MemberReceiveAddressDO, ReceiveAddressResponseDTO> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<ReceiveAddressResponseDTO> processor(Long id) {
        MemberReceiveAddressDO addressDO = memberReceiveAddressMapper.selectById(id);
        if (addressDO == null) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_NOT_EXIST);
        }
        // 判断只有当前会员才能查看
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        if (!loginMember.getId().equals(addressDO.getCreator())) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR);
        }
        return ResultUtil.success(doConvertDto(addressDO, ReceiveAddressResponseDTO.class));
    }

}

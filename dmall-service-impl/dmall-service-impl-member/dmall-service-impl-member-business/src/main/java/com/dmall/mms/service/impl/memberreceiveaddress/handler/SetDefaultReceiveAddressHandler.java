package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 设置默认地址处理器
 * @author: created by hang.yu on 2020/2/29 16:17
 */
@Component
public class SetDefaultReceiveAddressHandler extends AbstractCommonHandler<Long, MemberReceiveAddressDO, Long> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<Long> processor(Long id) {
        // 获取当前登录用户
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        // 地址必须存在且为当前登录人的
        MemberReceiveAddressDO receiveAddressDO = memberReceiveAddressMapper.selectById(id);
        if (receiveAddressDO == null) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_NOT_EXIST);
        }
        if (!loginMember.getId().equals(receiveAddressDO.getCreator())) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR);
        }
        // 先设置所有的地址为非默认
        MemberReceiveAddressDO addressDO = new MemberReceiveAddressDO();
        addressDO.setDefaultStatus(YNEnum.N.getCode());
        memberReceiveAddressMapper.update(addressDO, Wrappers.<MemberReceiveAddressDO>lambdaUpdate()
            .eq(MemberReceiveAddressDO::getCreator, loginMember.getId()));
        // 将当前地址设置为默认
        MemberReceiveAddressDO defaultAddress = new MemberReceiveAddressDO();
        defaultAddress.setId(id);
        defaultAddress.setDefaultStatus(YNEnum.Y.getCode());
        memberReceiveAddressMapper.updateById(defaultAddress);
        return ResultUtil.success(id);
    }
}

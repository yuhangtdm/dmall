package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberreceiveaddress.request.SaveMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员收货地址处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveMemberReceiveAddressHandler
    extends AbstractCommonHandler<SaveMemberReceiveAddressRequestDTO, MemberReceiveAddressDO, Long> {

    private static final Integer MAX_COUNT = 20;

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<Long> processor(SaveMemberReceiveAddressRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        // 限制地址最多20个
        Integer count = memberReceiveAddressMapper.selectCount(Wrappers.<MemberReceiveAddressDO>lambdaQuery()
            .eq(MemberReceiveAddressDO::getCreator, loginMember.getId()));
        if (count >= MAX_COUNT) {
            return ResultUtil.fail(MmsErrorEnum.MEMBER_RECEIVE_ADDRESS_COUNT_LIMIT);
        }
        // 如果是第一个地址 设为默认
        MemberReceiveAddressDO memberReceiveAddressDO = dtoConvertDo(requestDTO, MemberReceiveAddressDO.class);
        if (count == 0) {
            memberReceiveAddressDO.setDefaultStatus(YNEnum.Y.getCode());
        } else {
            // 其他地址 设置为非默认
            memberReceiveAddressDO.setDefaultStatus(YNEnum.N.getCode());
        }
        memberReceiveAddressMapper.insert(memberReceiveAddressDO);
        return ResultUtil.success(memberReceiveAddressDO.getId());
    }

}

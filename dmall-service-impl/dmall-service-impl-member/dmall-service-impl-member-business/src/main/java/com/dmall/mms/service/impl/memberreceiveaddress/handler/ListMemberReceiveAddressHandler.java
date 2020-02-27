package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressResponseDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.ListMemberReceiveAddressRequestDTO;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员收货地址列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListMemberReceiveAddressHandler extends AbstractCommonHandler<ListMemberReceiveAddressRequestDTO, MemberReceiveAddressDO, CommonMemberReceiveAddressResponseDTO> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<List<CommonMemberReceiveAddressResponseDTO>> validate(ListMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberReceiveAddressResponseDTO>> processor(ListMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

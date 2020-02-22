package com.dmall.mms.service.impl.memberreceiveaddress.handler;

import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressResponseDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.PageMemberReceiveAddressRequestDTO;
import com.dmall.mms.generator.dataobject.MemberReceiveAddressDO;
import com.dmall.mms.generator.mapper.MemberReceiveAddressMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员收货地址分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class PageMemberReceiveAddressHandler extends AbstractCommonHandler<PageMemberReceiveAddressRequestDTO, MemberReceiveAddressDO, CommonMemberReceiveAddressResponseDTO> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberReceiveAddressResponseDTO>> validate(PageMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberReceiveAddressResponseDTO>> processor(PageMemberReceiveAddressRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

package com.dmall.mms.service.impl.membersafe.handler;

import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeResponseDTO;
import com.dmall.mms.api.dto.membersafe.request.PageMemberSafeRequestDTO;
import com.dmall.mms.generator.dataobject.MemberSafeDO;
import com.dmall.mms.generator.mapper.MemberSafeMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 账户安全分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class PageMemberSafeHandler extends AbstractCommonHandler<PageMemberSafeRequestDTO, MemberSafeDO, CommonMemberSafeResponseDTO> {

    @Autowired
    private MemberSafeMapper memberSafeMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberSafeResponseDTO>> validate(PageMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberSafeResponseDTO>> processor(PageMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

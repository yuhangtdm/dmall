package com.dmall.mms.service.impl.membersafe.handler;

import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeResponseDTO;
import com.dmall.mms.api.dto.membersafe.request.ListMemberSafeRequestDTO;
import com.dmall.mms.generator.dataobject.MemberSafeDO;
import com.dmall.mms.generator.mapper.MemberSafeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 账户安全列表处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class ListMemberSafeHandler extends AbstractCommonHandler<ListMemberSafeRequestDTO, MemberSafeDO, CommonMemberSafeResponseDTO> {

    @Autowired
    private MemberSafeMapper memberSafeMapper;

    @Override
    public BaseResult<List<CommonMemberSafeResponseDTO>> validate(ListMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberSafeResponseDTO>> processor(ListMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

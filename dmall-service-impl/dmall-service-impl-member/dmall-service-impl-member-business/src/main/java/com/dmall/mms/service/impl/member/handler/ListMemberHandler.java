package com.dmall.mms.service.impl.member.handler;

import com.dmall.mms.api.dto.member.common.CommonMemberResponseDTO;
import com.dmall.mms.api.dto.member.request.ListMemberRequestDTO;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员列表处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class ListMemberHandler extends AbstractCommonHandler<ListMemberRequestDTO, MemberDO, CommonMemberResponseDTO> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<List<CommonMemberResponseDTO>> validate(ListMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberResponseDTO>> processor(ListMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

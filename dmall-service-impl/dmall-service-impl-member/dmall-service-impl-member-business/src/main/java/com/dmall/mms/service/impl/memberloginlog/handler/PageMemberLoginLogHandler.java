package com.dmall.mms.service.impl.memberloginlog.handler;

import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogResponseDTO;
import com.dmall.mms.api.dto.memberloginlog.request.PageMemberLoginLogRequestDTO;
import com.dmall.mms.generator.dataobject.MemberLoginLogDO;
import com.dmall.mms.generator.mapper.MemberLoginLogMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员登录记录分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageMemberLoginLogHandler extends AbstractCommonHandler<PageMemberLoginLogRequestDTO, MemberLoginLogDO, CommonMemberLoginLogResponseDTO> {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberLoginLogResponseDTO>> validate(PageMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberLoginLogResponseDTO>> processor(PageMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

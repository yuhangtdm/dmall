package com.dmall.mms.service.impl.memberloginlog.handler;

import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogResponseDTO;
import com.dmall.mms.api.dto.memberloginlog.request.ListMemberLoginLogRequestDTO;
import com.dmall.mms.service.impl.memberloginlog.enums.MemberLoginLogErrorEnum;
import com.dmall.mms.generator.dataobject.MemberLoginLogDO;
import com.dmall.mms.generator.mapper.MemberLoginLogMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员登录记录列表处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class ListMemberLoginLogHandler extends AbstractCommonHandler<ListMemberLoginLogRequestDTO, MemberLoginLogDO, CommonMemberLoginLogResponseDTO> {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<List<CommonMemberLoginLogResponseDTO>> validate(ListMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberLoginLogResponseDTO>> processor(ListMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

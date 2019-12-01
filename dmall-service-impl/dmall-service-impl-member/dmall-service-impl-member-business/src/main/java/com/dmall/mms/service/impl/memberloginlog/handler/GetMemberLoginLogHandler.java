package com.dmall.mms.service.impl.memberloginlog.handler;

import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogResponseDTO;
import com.dmall.mms.service.impl.memberloginlog.enums.MemberLoginLogErrorEnum;
import com.dmall.mms.generator.dataobject.MemberLoginLogDO;
import com.dmall.mms.generator.mapper.MemberLoginLogMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询会员登录记录处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class GetMemberLoginLogHandler extends AbstractCommonHandler<Long, MemberLoginLogDO, CommonMemberLoginLogResponseDTO> {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<CommonMemberLoginLogResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonMemberLoginLogResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}

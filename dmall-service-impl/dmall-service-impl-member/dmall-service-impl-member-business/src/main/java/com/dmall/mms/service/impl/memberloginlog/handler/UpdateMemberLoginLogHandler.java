package com.dmall.mms.service.impl.memberloginlog.handler;

import com.dmall.mms.api.dto.memberloginlog.request.UpdateMemberLoginLogRequestDTO;
import com.dmall.mms.service.impl.memberloginlog.enums.MemberLoginLogErrorEnum;
import com.dmall.mms.generator.dataobject.MemberLoginLogDO;
import com.dmall.mms.generator.mapper.MemberLoginLogMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员登录记录处理器
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Component
public class UpdateMemberLoginLogHandler extends AbstractCommonHandler<UpdateMemberLoginLogRequestDTO, MemberLoginLogDO, Long> {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
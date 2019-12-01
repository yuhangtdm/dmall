package com.dmall.mms.service.impl.memberloginlog.handler;

import com.dmall.mms.api.dto.memberloginlog.request.SaveMemberLoginLogRequestDTO;
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
 * @description: 新增会员登录记录处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class SaveMemberLoginLogHandler extends AbstractCommonHandler<SaveMemberLoginLogRequestDTO, MemberLoginLogDO, Long> {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

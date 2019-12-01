package com.dmall.mms.service.impl.memberviewsku.handler;

import com.dmall.mms.service.impl.memberviewsku.enums.MemberViewSkuErrorEnum;
import com.dmall.mms.generator.dataobject.MemberViewSkuDO;
import com.dmall.mms.generator.mapper.MemberViewSkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除会员浏览历史记录处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class DeleteMemberViewSkuHandler extends AbstractCommonHandler<Long, MemberViewSkuDO, Long> {

    @Autowired
    private MemberViewSkuMapper memberViewSkuMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}

package com.dmall.mms.service.impl.advice.handler;

import com.dmall.mms.api.dto.advice.common.CommonAdviceResponseDTO;
import com.dmall.mms.service.impl.advice.enums.AdviceErrorEnum;
import com.dmall.mms.generator.dataobject.AdviceDO;
import com.dmall.mms.generator.mapper.AdviceMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询会员意见表 处理器
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Component
public class GetAdviceHandler extends AbstractCommonHandler<Long, AdviceDO, CommonAdviceResponseDTO> {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public BaseResult<CommonAdviceResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonAdviceResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}

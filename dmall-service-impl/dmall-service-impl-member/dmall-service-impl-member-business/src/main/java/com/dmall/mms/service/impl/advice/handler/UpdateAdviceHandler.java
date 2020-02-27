package com.dmall.mms.service.impl.advice.handler;

import com.dmall.mms.api.dto.advice.request.UpdateAdviceRequestDTO;
import com.dmall.mms.service.impl.advice.enums.AdviceErrorEnum;
import com.dmall.mms.generator.dataobject.AdviceDO;
import com.dmall.mms.generator.mapper.AdviceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员意见表 处理器
 * @author: created by hang.yu on 2020-02-23 19:41:02
 */
@Component
public class UpdateAdviceHandler extends AbstractCommonHandler<UpdateAdviceRequestDTO, AdviceDO, Long> {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public BaseResult<Long> validate(UpdateAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
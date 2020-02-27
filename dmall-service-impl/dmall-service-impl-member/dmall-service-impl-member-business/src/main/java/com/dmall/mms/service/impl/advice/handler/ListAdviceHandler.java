package com.dmall.mms.service.impl.advice.handler;

import com.dmall.mms.api.dto.advice.common.CommonAdviceResponseDTO;
import com.dmall.mms.api.dto.advice.request.ListAdviceRequestDTO;
import com.dmall.mms.generator.dataobject.AdviceDO;
import com.dmall.mms.generator.mapper.AdviceMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员意见表 列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:02
 */
@Component
public class ListAdviceHandler extends AbstractCommonHandler<ListAdviceRequestDTO, AdviceDO, CommonAdviceResponseDTO> {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public BaseResult<List<CommonAdviceResponseDTO>> validate(ListAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonAdviceResponseDTO>> processor(ListAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

package com.dmall.mms.service.impl.advice.handler;

import com.dmall.mms.api.dto.advice.common.CommonAdviceResponseDTO;
import com.dmall.mms.api.dto.advice.request.PageAdviceRequestDTO;
import com.dmall.mms.generator.dataobject.AdviceDO;
import com.dmall.mms.generator.mapper.AdviceMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员意见表 分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Component
public class PageAdviceHandler extends AbstractCommonHandler<PageAdviceRequestDTO, AdviceDO, CommonAdviceResponseDTO> {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public BaseResult<LayUiPage<CommonAdviceResponseDTO>> validate(PageAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonAdviceResponseDTO>> processor(PageAdviceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

package com.dmall.mms.service.impl.memberstatisticsinfo.handler;

import com.dmall.mms.api.dto.memberstatisticsinfo.common.CommonMemberStatisticsInfoResponseDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.PageMemberStatisticsInfoRequestDTO;
import com.dmall.mms.generator.dataobject.MemberStatisticsInfoDO;
import com.dmall.mms.generator.mapper.MemberStatisticsInfoMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员统计信息分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class PageMemberStatisticsInfoHandler extends AbstractCommonHandler<PageMemberStatisticsInfoRequestDTO, MemberStatisticsInfoDO, CommonMemberStatisticsInfoResponseDTO> {

    @Autowired
    private MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberStatisticsInfoResponseDTO>> validate(PageMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberStatisticsInfoResponseDTO>> processor(PageMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

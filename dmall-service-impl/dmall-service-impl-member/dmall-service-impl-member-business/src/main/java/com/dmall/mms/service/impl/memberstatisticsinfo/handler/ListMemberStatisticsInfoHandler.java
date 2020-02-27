package com.dmall.mms.service.impl.memberstatisticsinfo.handler;

import com.dmall.mms.api.dto.memberstatisticsinfo.common.CommonMemberStatisticsInfoResponseDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.ListMemberStatisticsInfoRequestDTO;
import com.dmall.mms.generator.dataobject.MemberStatisticsInfoDO;
import com.dmall.mms.generator.mapper.MemberStatisticsInfoMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员统计信息列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListMemberStatisticsInfoHandler extends AbstractCommonHandler<ListMemberStatisticsInfoRequestDTO, MemberStatisticsInfoDO, CommonMemberStatisticsInfoResponseDTO> {

    @Autowired
    private MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    @Override
    public BaseResult<List<CommonMemberStatisticsInfoResponseDTO>> validate(ListMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberStatisticsInfoResponseDTO>> processor(ListMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}

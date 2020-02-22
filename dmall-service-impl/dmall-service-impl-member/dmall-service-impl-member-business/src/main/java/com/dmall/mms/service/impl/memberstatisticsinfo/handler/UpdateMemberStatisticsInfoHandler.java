package com.dmall.mms.service.impl.memberstatisticsinfo.handler;

import com.dmall.mms.api.dto.memberstatisticsinfo.request.UpdateMemberStatisticsInfoRequestDTO;
import com.dmall.mms.service.impl.memberstatisticsinfo.enums.MemberStatisticsInfoErrorEnum;
import com.dmall.mms.generator.dataobject.MemberStatisticsInfoDO;
import com.dmall.mms.generator.mapper.MemberStatisticsInfoMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员统计信息处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class UpdateMemberStatisticsInfoHandler extends AbstractCommonHandler<UpdateMemberStatisticsInfoRequestDTO, MemberStatisticsInfoDO, Long> {

    @Autowired
    private MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberStatisticsInfoRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
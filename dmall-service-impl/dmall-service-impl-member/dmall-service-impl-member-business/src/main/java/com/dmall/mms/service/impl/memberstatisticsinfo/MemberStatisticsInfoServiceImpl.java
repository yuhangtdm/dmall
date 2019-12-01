package com.dmall.mms.service.impl.memberstatisticsinfo;

import com.dmall.mms.api.dto.memberstatisticsinfo.request.SaveMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.UpdateMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.ListMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.request.PageMemberStatisticsInfoRequestDTO;
import com.dmall.mms.api.dto.memberstatisticsinfo.common.CommonMemberStatisticsInfoResponseDTO;
import com.dmall.mms.api.service.MemberStatisticsInfoService;
import com.dmall.mms.service.impl.memberstatisticsinfo.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员统计信息服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberStatisticsInfoServiceImpl implements MemberStatisticsInfoService {

    @Autowired
    protected SaveMemberStatisticsInfoHandler saveMemberStatisticsInfoHandler;

    @Autowired
    private DeleteMemberStatisticsInfoHandler deleteMemberStatisticsInfoHandler;

    @Autowired
    private UpdateMemberStatisticsInfoHandler updateMemberStatisticsInfoHandler;

    @Autowired
    private GetMemberStatisticsInfoHandler getMemberStatisticsInfoHandler;

    @Autowired
    private ListMemberStatisticsInfoHandler listMemberStatisticsInfoHandler;

    @Autowired
    private PageMemberStatisticsInfoHandler pageMemberStatisticsInfoHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberStatisticsInfoRequestDTO requestDTO) {
        return saveMemberStatisticsInfoHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberStatisticsInfoHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberStatisticsInfoRequestDTO requestDTO) {
        return updateMemberStatisticsInfoHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberStatisticsInfoResponseDTO> get(Long id) {
        return getMemberStatisticsInfoHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberStatisticsInfoResponseDTO>> list(@RequestBody ListMemberStatisticsInfoRequestDTO requestDTO) {
        return listMemberStatisticsInfoHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberStatisticsInfoResponseDTO>> page(@RequestBody PageMemberStatisticsInfoRequestDTO requestDTO) {
        return pageMemberStatisticsInfoHandler.handler(requestDTO);
    }

}

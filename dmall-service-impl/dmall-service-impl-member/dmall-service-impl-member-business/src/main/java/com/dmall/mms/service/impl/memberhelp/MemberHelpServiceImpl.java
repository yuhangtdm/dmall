package com.dmall.mms.service.impl.memberhelp;

import com.dmall.mms.api.dto.memberhelp.request.SaveMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.UpdateMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.ListMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.request.PageMemberHelpRequestDTO;
import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpResponseDTO;
import com.dmall.mms.api.service.MemberHelpService;
import com.dmall.mms.service.impl.memberhelp.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 会员-帮助关系表 帮助对会员有用服务实现
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@RestController
public class MemberHelpServiceImpl implements MemberHelpService {

    @Autowired
    private SaveMemberHelpHandler saveMemberHelpHandler;

    @Autowired
    private DeleteMemberHelpHandler deleteMemberHelpHandler;

    @Autowired
    private UpdateMemberHelpHandler updateMemberHelpHandler;

    @Autowired
    private GetMemberHelpHandler getMemberHelpHandler;

    @Autowired
    private ListMemberHelpHandler listMemberHelpHandler;

    @Autowired
    private PageMemberHelpHandler pageMemberHelpHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberHelpRequestDTO requestDTO) {
        return saveMemberHelpHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMemberHelpHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMemberHelpRequestDTO requestDTO) {
        return updateMemberHelpHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberHelpResponseDTO> get(Long id) {
        return getMemberHelpHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberHelpResponseDTO>> list(@RequestBody ListMemberHelpRequestDTO requestDTO) {
        return listMemberHelpHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberHelpResponseDTO>> page(@RequestBody PageMemberHelpRequestDTO requestDTO) {
        return pageMemberHelpHandler.handler(requestDTO);
    }

}

package com.dmall.mms.service.impl.member;

import com.dmall.mms.api.dto.member.request.SaveMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.UpdateMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.ListMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.PageMemberRequestDTO;
import com.dmall.mms.api.dto.member.common.CommonMemberResponseDTO;
import com.dmall.mms.api.service.MemberService;
import com.dmall.mms.service.impl.member.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@RestController
public class  MemberServiceImpl implements MemberService {

    @Autowired
    protected SaveMemberHandler saveMemberHandler;

    @Autowired
    private DeleteMemberHandler deleteMemberHandler;

    @Autowired
    private UpdateMemberHandler updateMemberHandler;

    @Autowired
    private GetMemberHandler getMemberHandler;

    @Autowired
    private ListMemberHandler listMemberHandler;

    @Autowired
    private PageMemberHandler pageMemberHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberRequestDTO requestDTO) {
        return saveMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberRequestDTO requestDTO) {
        return updateMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberResponseDTO> get(Long id) {
        return getMemberHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberResponseDTO>> list(@RequestBody ListMemberRequestDTO requestDTO) {
        return listMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberResponseDTO>> page(@RequestBody PageMemberRequestDTO requestDTO) {
        return pageMemberHandler.handler(requestDTO);
    }

}

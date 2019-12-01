package com.dmall.mms.service.impl.membercolletionsubject;

import com.dmall.mms.api.dto.membercolletionsubject.request.SaveMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.UpdateMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.ListMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.PageMemberColletionSubjectRequestDTO;
import com.dmall.mms.api.dto.membercolletionsubject.common.CommonMemberColletionSubjectResponseDTO;
import com.dmall.mms.api.service.MemberColletionSubjectService;
import com.dmall.mms.service.impl.membercolletionsubject.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收藏专题表 服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberColletionSubjectServiceImpl implements MemberColletionSubjectService {

    @Autowired
    protected SaveMemberColletionSubjectHandler saveMemberColletionSubjectHandler;

    @Autowired
    private DeleteMemberColletionSubjectHandler deleteMemberColletionSubjectHandler;

    @Autowired
    private UpdateMemberColletionSubjectHandler updateMemberColletionSubjectHandler;

    @Autowired
    private GetMemberColletionSubjectHandler getMemberColletionSubjectHandler;

    @Autowired
    private ListMemberColletionSubjectHandler listMemberColletionSubjectHandler;

    @Autowired
    private PageMemberColletionSubjectHandler pageMemberColletionSubjectHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberColletionSubjectRequestDTO requestDTO) {
        return saveMemberColletionSubjectHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberColletionSubjectHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberColletionSubjectRequestDTO requestDTO) {
        return updateMemberColletionSubjectHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberColletionSubjectResponseDTO> get(Long id) {
        return getMemberColletionSubjectHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberColletionSubjectResponseDTO>> list(@RequestBody ListMemberColletionSubjectRequestDTO requestDTO) {
        return listMemberColletionSubjectHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberColletionSubjectResponseDTO>> page(@RequestBody PageMemberColletionSubjectRequestDTO requestDTO) {
        return pageMemberColletionSubjectHandler.handler(requestDTO);
    }

}

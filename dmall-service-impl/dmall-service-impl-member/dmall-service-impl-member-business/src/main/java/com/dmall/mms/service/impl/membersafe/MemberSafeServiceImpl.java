package com.dmall.mms.service.impl.membersafe;

import com.dmall.mms.api.dto.membersafe.request.SaveMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.request.UpdateMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.request.ListMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.request.PageMemberSafeRequestDTO;
import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeResponseDTO;
import com.dmall.mms.api.service.MemberSafeService;
import com.dmall.mms.service.impl.membersafe.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 账户安全服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberSafeServiceImpl implements MemberSafeService {

    @Autowired
    protected SaveMemberSafeHandler saveMemberSafeHandler;

    @Autowired
    private DeleteMemberSafeHandler deleteMemberSafeHandler;

    @Autowired
    private UpdateMemberSafeHandler updateMemberSafeHandler;

    @Autowired
    private GetMemberSafeHandler getMemberSafeHandler;

    @Autowired
    private ListMemberSafeHandler listMemberSafeHandler;

    @Autowired
    private PageMemberSafeHandler pageMemberSafeHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberSafeRequestDTO requestDTO) {
        return saveMemberSafeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberSafeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberSafeRequestDTO requestDTO) {
        return updateMemberSafeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberSafeResponseDTO> get(Long id) {
        return getMemberSafeHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberSafeResponseDTO>> list(@RequestBody ListMemberSafeRequestDTO requestDTO) {
        return listMemberSafeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberSafeResponseDTO>> page(@RequestBody PageMemberSafeRequestDTO requestDTO) {
        return pageMemberSafeHandler.handler(requestDTO);
    }

}

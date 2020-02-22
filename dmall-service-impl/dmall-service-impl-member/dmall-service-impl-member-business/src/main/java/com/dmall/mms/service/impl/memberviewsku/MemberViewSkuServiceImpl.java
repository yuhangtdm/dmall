package com.dmall.mms.service.impl.memberviewsku;

import com.dmall.mms.api.dto.memberviewsku.request.SaveMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.UpdateMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.ListMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.PageMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.service.MemberViewSkuService;
import com.dmall.mms.service.impl.memberviewsku.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 会员浏览历史记录服务实现
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@RestController
public class MemberViewSkuServiceImpl implements MemberViewSkuService {

    @Autowired
    private SaveMemberViewSkuHandler saveMemberViewSkuHandler;

    @Autowired
    private DeleteMemberViewSkuHandler deleteMemberViewSkuHandler;

    @Autowired
    private UpdateMemberViewSkuHandler updateMemberViewSkuHandler;

    @Autowired
    private GetMemberViewSkuHandler getMemberViewSkuHandler;

    @Autowired
    private ListMemberViewSkuHandler listMemberViewSkuHandler;

    @Autowired
    private PageMemberViewSkuHandler pageMemberViewSkuHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberViewSkuRequestDTO requestDTO) {
        return saveMemberViewSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMemberViewSkuHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMemberViewSkuRequestDTO requestDTO) {
        return updateMemberViewSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberViewSkuResponseDTO> get(Long id) {
        return getMemberViewSkuHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberViewSkuResponseDTO>> list(@RequestBody ListMemberViewSkuRequestDTO requestDTO) {
        return listMemberViewSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberViewSkuResponseDTO>> page(@RequestBody PageMemberViewSkuRequestDTO requestDTO) {
        return pageMemberViewSkuHandler.handler(requestDTO);
    }

}

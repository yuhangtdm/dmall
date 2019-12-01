package com.dmall.mms.service.impl.memberviewsku;

import com.dmall.mms.api.dto.memberviewsku.request.SaveMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.UpdateMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.ListMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.request.PageMemberViewSkuRequestDTO;
import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.service.MemberViewSkuService;
import com.dmall.mms.service.impl.memberviewsku.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员浏览历史记录服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberViewSkuServiceImpl implements MemberViewSkuService {

    @Autowired
    protected SaveMemberViewSkuHandler saveMemberViewSkuHandler;

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
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberViewSkuHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberViewSkuRequestDTO requestDTO) {
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
    public BaseResult<LayuiPage<CommonMemberViewSkuResponseDTO>> page(@RequestBody PageMemberViewSkuRequestDTO requestDTO) {
        return pageMemberViewSkuHandler.handler(requestDTO);
    }

}

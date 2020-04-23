package com.dmall.mms.service.impl.memberinvoice;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.memberinvoice.MemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.api.service.MemberInvoiceService;
import com.dmall.mms.service.impl.memberinvoice.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员发票服务实现
 * @author: created by hang.yu on 2020/3/28 10:22
 */
@RestController
public class MemberInvoiceServiceImpl implements MemberInvoiceService {

    @Autowired
    private SaveMemberInvoiceHandler saveMemberInvoiceHandler;

    @Autowired
    private UpdateMemberInvoiceHandler updateMemberInvoiceHandler;

    @Autowired
    private GetMemberInvoiceHandler getMemberInvoiceHandler;

    @Autowired
    private DeleteMemberInvoiceHandler deleteMemberInvoiceHandler;

    @Autowired
    private GetMemberInvoiceByMemberIdHandler getMemberInvoiceByMemberIdHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberInvoiceRequestDTO requestDTO) {
        return saveMemberInvoiceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMemberInvoiceRequestDTO requestDTO) {
        return updateMemberInvoiceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMemberInvoiceHandler.handler(id);
    }

    @Override
    public BaseResult<MemberInvoiceResponseDTO> get(Long id) {
        return getMemberInvoiceHandler.handler(id);
    }

    @Override
    public BaseResult<MemberInvoiceResponseDTO> getByMemberId(Long memberId) {
        return getMemberInvoiceByMemberIdHandler.handler(memberId);
    }
}

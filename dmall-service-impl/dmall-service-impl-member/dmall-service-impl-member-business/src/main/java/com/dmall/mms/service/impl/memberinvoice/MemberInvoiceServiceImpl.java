package com.dmall.mms.service.impl.memberinvoice;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.memberinvoice.GetMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.api.service.MemberInvoiceService;
import com.dmall.mms.service.impl.memberinvoice.handler.DeleteMemberInvoiceHandler;
import com.dmall.mms.service.impl.memberinvoice.handler.GetMemberInvoiceHandler;
import com.dmall.mms.service.impl.memberinvoice.handler.SaveMemberInvoiceHandler;
import com.dmall.mms.service.impl.memberinvoice.handler.UpdateMemberInvoiceHandler;
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
    public BaseResult<GetMemberInvoiceResponseDTO> get(Long id) {
        return getMemberInvoiceHandler.handler(id);
    }

    @Override
    public BaseResult<GetMemberInvoiceResponseDTO> getByMemberId(Long memberId) {
        return null;
    }
}

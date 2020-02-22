package com.dmall.mms.service.impl.memberinvoice;

import com.dmall.mms.api.dto.memberinvoice.request.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.request.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.request.ListMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.request.PageMemberInvoiceRequestDTO;
import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceResponseDTO;
import com.dmall.mms.api.service.MemberInvoiceService;
import com.dmall.mms.service.impl.memberinvoice.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 会员发票服务实现
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@RestController
public class MemberInvoiceServiceImpl implements MemberInvoiceService {

    @Autowired
    private SaveMemberInvoiceHandler saveMemberInvoiceHandler;

    @Autowired
    private DeleteMemberInvoiceHandler deleteMemberInvoiceHandler;

    @Autowired
    private UpdateMemberInvoiceHandler updateMemberInvoiceHandler;

    @Autowired
    private GetMemberInvoiceHandler getMemberInvoiceHandler;

    @Autowired
    private ListMemberInvoiceHandler listMemberInvoiceHandler;

    @Autowired
    private PageMemberInvoiceHandler pageMemberInvoiceHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberInvoiceRequestDTO requestDTO) {
        return saveMemberInvoiceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMemberInvoiceHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMemberInvoiceRequestDTO requestDTO) {
        return updateMemberInvoiceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberInvoiceResponseDTO> get(Long id) {
        return getMemberInvoiceHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberInvoiceResponseDTO>> list(@RequestBody ListMemberInvoiceRequestDTO requestDTO) {
        return listMemberInvoiceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberInvoiceResponseDTO>> page(@RequestBody PageMemberInvoiceRequestDTO requestDTO) {
        return pageMemberInvoiceHandler.handler(requestDTO);
    }

}

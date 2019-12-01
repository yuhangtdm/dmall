package com.dmall.mms.service.impl.memberreceiveaddress;

import com.dmall.mms.api.dto.memberreceiveaddress.request.SaveMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.ListMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.PageMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.common.CommonMemberReceiveAddressResponseDTO;
import com.dmall.mms.api.service.MemberReceiveAddressService;
import com.dmall.mms.service.impl.memberreceiveaddress.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收货地址服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberReceiveAddressServiceImpl implements MemberReceiveAddressService {

    @Autowired
    protected SaveMemberReceiveAddressHandler saveMemberReceiveAddressHandler;

    @Autowired
    private DeleteMemberReceiveAddressHandler deleteMemberReceiveAddressHandler;

    @Autowired
    private UpdateMemberReceiveAddressHandler updateMemberReceiveAddressHandler;

    @Autowired
    private GetMemberReceiveAddressHandler getMemberReceiveAddressHandler;

    @Autowired
    private ListMemberReceiveAddressHandler listMemberReceiveAddressHandler;

    @Autowired
    private PageMemberReceiveAddressHandler pageMemberReceiveAddressHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberReceiveAddressRequestDTO requestDTO) {
        return saveMemberReceiveAddressHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberReceiveAddressHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberReceiveAddressRequestDTO requestDTO) {
        return updateMemberReceiveAddressHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberReceiveAddressResponseDTO> get(Long id) {
        return getMemberReceiveAddressHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberReceiveAddressResponseDTO>> list(@RequestBody ListMemberReceiveAddressRequestDTO requestDTO) {
        return listMemberReceiveAddressHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberReceiveAddressResponseDTO>> page(@RequestBody PageMemberReceiveAddressRequestDTO requestDTO) {
        return pageMemberReceiveAddressHandler.handler(requestDTO);
    }

}

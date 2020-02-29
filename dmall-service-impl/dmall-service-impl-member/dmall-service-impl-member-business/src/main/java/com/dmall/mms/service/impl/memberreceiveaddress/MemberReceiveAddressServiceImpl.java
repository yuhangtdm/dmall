package com.dmall.mms.service.impl.memberreceiveaddress;

import com.dmall.mms.api.dto.memberreceiveaddress.request.SaveMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.request.UpdateMemberReceiveAddressRequestDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.response.GetReceiveAddressResponseDTO;
import com.dmall.mms.api.dto.memberreceiveaddress.response.ListReceiveAddressResponseDTO;
import com.dmall.mms.api.service.MemberReceiveAddressService;
import com.dmall.mms.service.impl.memberreceiveaddress.handler.*;
import com.dmall.common.dto.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收货地址服务实现
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@RestController
public class MemberReceiveAddressServiceImpl implements MemberReceiveAddressService {

    @Autowired
    private ListMemberReceiveAddressHandler listMemberReceiveAddressHandler;

    @Autowired
    private SaveMemberReceiveAddressHandler saveMemberReceiveAddressHandler;

    @Autowired
    private UpdateMemberReceiveAddressHandler updateMemberReceiveAddressHandler;

    @Autowired
    private DeleteMemberReceiveAddressHandler deleteMemberReceiveAddressHandler;

    @Autowired
    private SetDefaultReceiveAddressHandler setDefaultReceiveAddressHandler;

    @Autowired
    private GetMemberReceiveAddressHandler getMemberReceiveAddressHandler;

    @Override
    public BaseResult<List<ListReceiveAddressResponseDTO>> list() {
        return listMemberReceiveAddressHandler.handler(null);
    }

    @Override
    public BaseResult<Long> save(@Valid SaveMemberReceiveAddressRequestDTO requestDTO) {
        return saveMemberReceiveAddressHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberReceiveAddressRequestDTO requestDTO) {
        return updateMemberReceiveAddressHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMemberReceiveAddressHandler.handler(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> setDefault(Long id) {
        return setDefaultReceiveAddressHandler.handler(id);
    }

    @Override
    public BaseResult<GetReceiveAddressResponseDTO> get(Long id) {
        return getMemberReceiveAddressHandler.handler(id);
    }
}

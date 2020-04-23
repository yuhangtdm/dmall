package com.dmall.mms.service.impl.member;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.mms.api.dto.member.WeiBoLoginRequestDTO;
import com.dmall.mms.api.service.ThirdPartyPlatformService;
import com.dmall.mms.service.impl.member.handler.WeiBoRegisterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: ThirdPartyPlatformServiceImpl
 * @author: created by hang.yu on 2020/3/1 21:48
 */
@RestController
public class ThirdPartyPlatformServiceImpl implements ThirdPartyPlatformService {

    @Autowired
    private WeiBoRegisterHandler weiBoRegisterHandler;

    @Override
    public BaseResult<PortalMemberDTO> weiBoLogin(@Valid WeiBoLoginRequestDTO requestDTO) {
        return weiBoRegisterHandler.handler(requestDTO);
    }
}

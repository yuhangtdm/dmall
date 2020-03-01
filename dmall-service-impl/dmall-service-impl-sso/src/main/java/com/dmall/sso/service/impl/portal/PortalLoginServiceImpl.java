package com.dmall.sso.service.impl.portal;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.sso.api.dto.portal.PortalLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.api.dto.portal.WeiBolLoginRequestDTO;
import com.dmall.sso.api.service.PortalLoginService;
import com.dmall.sso.service.impl.portal.handler.CheckTokenHandler;
import com.dmall.sso.service.impl.portal.handler.LoginHandler;
import com.dmall.sso.service.impl.portal.handler.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: PortalLoginServiceImpl
 * @author: created by hang.yu on 2020/2/26 20:08
 */
@DS("portal")
@RestController
public class PortalLoginServiceImpl implements PortalLoginService {

    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private CheckTokenHandler checkTokenHandler;

    @Override
    public BaseResult<PortalLoginResponseDTO> login(@Valid PortalLoginRequestDTO requestDTO) {
        return loginHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Void> logout(String token) {
        return logoutHandler.handler(token);
    }

    @Override
    public BaseResult<PortalMemberDTO> checkToken(String token) {
        return checkTokenHandler.handler(token);
    }

}

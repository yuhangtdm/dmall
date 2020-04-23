package com.dmall.mms.service.impl.member;

import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.member.RegisterMemberRequestDTO;
import com.dmall.mms.api.dto.member.ResetPasswordRequestDTO;
import com.dmall.mms.api.dto.member.UpdatePasswordRequestDTO;
import com.dmall.mms.api.service.MemberService;
import com.dmall.mms.service.impl.member.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员服务实现
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RegisterSendCodeHandler registerSendCodeHandler;

    @Autowired
    private CheckCodeHandler checkCodeHandler;

    @Autowired
    private RegisterMemberHandler registerMemberHandler;

    @Autowired
    private UpdatePasswordHandler updatePasswordHandler;

    @Autowired
    private ForgetPasswordSendCodeHandler forgetPasswordSendCodeHandler;

    @Autowired
    private ResetPasswordHandler resetPasswordHandler;

    @Override
    public BaseResult<Void> registerCode(String email) {
        return registerSendCodeHandler.handler(email);
    }

    @Override
    public BaseResult<String> checkCode(String email, String code) {
        return checkCodeHandler.checkCode(email, code);
    }

    @Override
    public BaseResult<Long> register(@RequestBody RegisterMemberRequestDTO requestDTO) {
        return registerMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<String> updatePassword(@RequestBody UpdatePasswordRequestDTO requestDTO) {
        return updatePasswordHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> updatePasswordCode(@RequestBody String email) {
        return forgetPasswordSendCodeHandler.handler(email);
    }

    @Override
    public BaseResult<Long> resetPassword(@RequestBody ResetPasswordRequestDTO requestDTO) {
        return resetPasswordHandler.handler(requestDTO);
    }

}

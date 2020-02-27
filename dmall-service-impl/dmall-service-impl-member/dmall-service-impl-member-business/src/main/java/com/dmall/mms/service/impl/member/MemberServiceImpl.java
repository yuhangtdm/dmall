package com.dmall.mms.service.impl.member;


import com.dmall.common.dto.BaseResult;
import com.dmall.mms.api.dto.member.request.ForgetPasswordRequestDTO;
import com.dmall.mms.api.dto.member.request.RegisterMemberRequestDTO;
import com.dmall.mms.api.dto.member.request.UpdatePasswordRequestDTO;
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
    public BaseResult<Long> updatePassword(@RequestBody UpdatePasswordRequestDTO requestDTO) {
        return updatePasswordHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> updatePasswordCode(@RequestBody String email) {
        return forgetPasswordSendCodeHandler.handler(email);
    }

    @Override
    public BaseResult<Long> forgetPassword(@RequestBody ForgetPasswordRequestDTO requestDTO) {
        return null;
    }
}

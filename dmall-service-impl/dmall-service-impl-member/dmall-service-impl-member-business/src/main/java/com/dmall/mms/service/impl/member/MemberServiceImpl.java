package com.dmall.mms.service.impl.member;

import com.dmall.mms.api.service.member.MemberService;
import com.dmall.mms.api.dto.member.request.MemberRequestDTO;
import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员服务实现类
 * @author: created by yuhang on 2019/10/15 22:35
 */
@RestController
@RefreshScope
public class MemberServiceImpl implements MemberService {

    @Value("${member.name}")
    private String memberName;

    @Override
    public MemberResponseDTO getMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(memberRequestDTO.getId());
        memberResponseDTO.setName(memberRequestDTO.getName());
        memberResponseDTO.setRealName("");
        memberResponseDTO.setGender(1);
        return memberResponseDTO;
    }
}

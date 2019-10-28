package com.dmall.service.impl.feign.member;

import com.dmall.service.api.member.MemberService;
import com.dmall.service.api.member.dto.request.MemberRequestDTO;
import com.dmall.service.api.member.dto.response.MemberResponseDTO;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by yuhang on 2019/10/15 23:20
 */
@Component
public class MemberFallbackService implements MemberService {

    @Override
    public MemberResponseDTO getMember(MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(0L);
        memberResponseDTO.setName("默认名称");
        memberResponseDTO.setRealName("默认名称");
        memberResponseDTO.setGender(0);

        return memberResponseDTO;
    }

}

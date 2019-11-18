package com.dmall.pms.generator.feign.member;

import com.dmall.mms.api.service.member.MemberService;
import com.dmall.mms.api.dto.member.request.MemberRequestDTO;
import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
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

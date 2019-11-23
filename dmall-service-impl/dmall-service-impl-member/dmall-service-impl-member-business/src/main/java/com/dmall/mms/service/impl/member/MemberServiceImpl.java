package com.dmall.mms.service.impl.member;

import com.dmall.mms.api.service.member.MemberService;
import com.dmall.mms.api.dto.member.request.MemberRequestDTO;
import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员服务实现类
 * @author: created by hang.yu on 2019/10/15 22:35
 */
@RestController
//@RefreshScope
public class MemberServiceImpl implements MemberService {

    @Override
    @Cacheable(cacheNames = "member",key = "1")
    public MemberResponseDTO getMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(memberRequestDTO.getId());
        memberResponseDTO.setName(memberRequestDTO.getName());
        memberResponseDTO.setRealName("");
        memberResponseDTO.setGender(1);
        return memberResponseDTO;
    }

    @Cacheable(cacheNames = "member")
    public String getName(String name){
        return name;
    }

    @Cacheable(cacheNames = "member")
    public Member getBean(Member member){
        return member;
    }

    @Cacheable(cacheNames = "member")
    public String getManyParams(String name, Integer age){
        return name + age;
    }


}

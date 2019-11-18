package com.dmall.pms.generator.feign.member;

import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by yuhang on 2019/10/27 12:29
 */
@Slf4j
@Component
public class MemberFallbackFactory implements FallbackFactory<MemberFeignService> {

    @Override
    public MemberFeignService create(Throwable throwable) {
        log.error("调用MemberService出现异常:", throwable);

        return memberRequestDTO -> {
            MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
            memberResponseDTO.setId(0L);
            memberResponseDTO.setName("FallbackFactory默认名称");
            memberResponseDTO.setRealName("FallbackFactory默认名称");
            memberResponseDTO.setGender(0);
            return memberResponseDTO;
        };
    }
}

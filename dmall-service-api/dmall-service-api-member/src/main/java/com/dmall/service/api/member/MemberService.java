package com.dmall.service.api.member;

import com.dmall.service.api.member.dto.request.MemberRequestDTO;
import com.dmall.service.api.member.dto.response.MemberResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 会员服务接口
 * @author: created by yuhang on 2019/10/15 22:23
 */
public interface MemberService {

    /**
     * 查询会员
     */
    @PostMapping("/member")
    @ResponseBody
    MemberResponseDTO getMember(@RequestBody MemberRequestDTO memberRequestDTO);

}

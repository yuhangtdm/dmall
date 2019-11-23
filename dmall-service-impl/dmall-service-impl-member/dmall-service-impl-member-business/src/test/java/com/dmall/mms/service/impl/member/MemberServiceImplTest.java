package com.dmall.mms.service.impl.member;

import com.dmall.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/10 15:12
 */
public class MemberServiceImplTest extends BaseTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    public void getName() {
        System.err.println(memberService.getName("张三"));
    }

    @Test
    public void getBean() {
        Member member = new Member();
        member.setId(1L);
        member.setName("李四");
        System.err.println(memberService.getBean(member));
    }

    @Test
    public void getManyParams() {
        System.err.println(memberService.getManyParams("王五", 30));
    }
}
package com.dmall.web.admin.member;

import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.exception.BusinessException;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/10 16:11
 */
@Controller
public class MemberController {

    @GetMapping("/")
    public String member(){
        return "index";
    }

    @GetMapping("/member/{id}")
    @ResponseBody
    public BaseResult<Member> member(@PathVariable Long id){
        Member member = new Member(id, "张三", 15);
        if (id == 2L){
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        return ResultUtil.success(member);
    }

    @PostMapping("/member")
    @ResponseBody
    public BaseResult<Member> member(@RequestBody Member member){
        return ResultUtil.success(member);
    }
}

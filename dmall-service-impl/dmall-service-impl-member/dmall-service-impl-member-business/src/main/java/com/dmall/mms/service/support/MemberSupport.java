package com.dmall.mms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: MemberSupport
 * @author: created by hang.yu on 2020/2/23 21:31
 */
@Component
public class MemberSupport {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 根据邮箱查询会员
     */
    public MemberDO getByEmail(String email) {
        return memberMapper.selectOne(Wrappers.<MemberDO>lambdaQuery().eq(MemberDO::getEmail, email));
    }

    /**
     * 根据手机号查询会员
     */
    public MemberDO getByPhone(String phone) {
        return memberMapper.selectOne(Wrappers.<MemberDO>lambdaQuery().eq(MemberDO::getPhone, phone));
    }

    /**
     * 根据名称查询会员
     */
    public MemberDO getByName(String memberName) {
        return memberMapper.selectOne(Wrappers.<MemberDO>lambdaQuery().eq(MemberDO::getMemberName, memberName));
    }

    /**
     * 根据微博id查询会员
     */
    public MemberDO getByWeiBoNo(Long weiBoId) {
        return memberMapper.selectOne(Wrappers.<MemberDO>lambdaQuery().eq(MemberDO::getWeiBoNo, weiBoId));
    }

}

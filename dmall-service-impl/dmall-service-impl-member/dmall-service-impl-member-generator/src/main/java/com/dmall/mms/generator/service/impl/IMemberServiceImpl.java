package com.dmall.mms.generator.service.impl;

import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.mms.generator.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 会员表
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Service
public class IMemberServiceImpl extends ServiceImpl<MemberMapper, MemberDO> implements IMemberService {

}

package com.dmall.mms.generator.service.impl;

import com.dmall.mms.generator.dataobject.MemberLoginLogDO;
import com.dmall.mms.generator.mapper.MemberLoginLogMapper;
import com.dmall.mms.generator.service.IMemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 会员登录记录表
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Service
public class IMemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLogDO>
    implements IMemberLoginLogService {

}

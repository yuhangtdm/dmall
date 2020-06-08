package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.UserLoginLogDO;
import com.dmall.bms.generator.mapper.UserLoginLogMapper;
import com.dmall.bms.generator.service.IUserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 后台用户登录日志表
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Service
public class IUserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLogDO>
    implements IUserLoginLogService {

}

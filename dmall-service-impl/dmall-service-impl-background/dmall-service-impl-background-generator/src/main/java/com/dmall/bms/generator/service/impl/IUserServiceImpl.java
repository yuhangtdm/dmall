package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 后台用户表
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

}

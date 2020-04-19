package com.dmall.demo.generator.service.impl;

import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import com.dmall.demo.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 后台用户表
 * @author: created by hang.yu on 2020-04-19 21:22:51
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

}

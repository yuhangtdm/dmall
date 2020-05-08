package com.dmall.bms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: UserSupport
 * @author: created by hang.yu on 2020/1/15 22:34
 */
@Component
public class UserSupport {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户信息
     */
    public UserDO getByPhone(String phone){
        return userMapper.selectOne(Wrappers.<UserDO>lambdaQuery()
            .eq(UserDO::getPhone,phone));
    }
}

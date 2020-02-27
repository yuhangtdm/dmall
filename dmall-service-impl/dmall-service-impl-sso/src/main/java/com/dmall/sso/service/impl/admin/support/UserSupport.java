package com.dmall.sso.service.impl.admin.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.sso.service.impl.admin.dataobject.UserDO;
import com.dmall.sso.service.impl.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: UserSupport
 * @author: created by hang.yu on 2020/1/11 16:44
 */
@Component
public class UserSupport {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户实体
     */
    public UserDO getByUserName(String userName) {
        return userMapper.selectOne(Wrappers.<UserDO>lambdaQuery()
                .eq(UserDO::getUserName, userName));
    }

}
